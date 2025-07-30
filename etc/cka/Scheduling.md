# Scheduling 

## Manual scheduling

기본적으로 pod defiition file 생성 시, 스케줄러는 파드를 할당할 수 있는 노드를 검사하고, 선정 알고리즘에 따라 적합한 노드를 생성한다.
이후, 파드의 spec.nodeName field에 선정된 Node Name을 스케줄러가 생성 및 삽입하면 파드가 해당 노드에 스케줄링 된다.

만약, 스케줄러가 없이 수동으로 스케줄링해야 한다면, 파드 생성 시, spec.nodeName 프로퍼티에 노드 이름을 지정하여 스케줄링 할 수 있다.

<br/>


<img width="324" height="315" alt="image" src="https://github.com/user-attachments/assets/41a82a51-7a24-4fd8-8419-1ea90778409c" />

<br/>
<br/>

그러나, 이미 생성된 파드의 경우에는, spec.nodeName을 변경할 수 없기 때문에, 이 경우에는 Binding Object를 생성 후, binding api를 호출해줄 수 있다.

<br/>
<br/>


<img width="941" height="502" alt="image" src="https://github.com/user-attachments/assets/5172c578-3e08-4651-96f8-8e761ed208eb" />


<br/>
</br>

## Labels & Selectors

Label이 필요한 이유는, 수많은 오브젝트를 일정한 기준에 따라 선정하거나, 필터링할 수 있는 수단이 필요하기 때문이다.
이렇게 label이 각 오브젝트에 할당되면, Selector를 이용해, 대상을 선정 및 필터링 할 수 있다. 
이때, Label은 원하는 만큼, 필요에 따라 여러 개를 오브젝트에 할당할 수 있다.

<br/>
</br>

<img width="470" height="434" alt="image" src="https://github.com/user-attachments/assets/3e7e9777-c2ba-447f-817f-e48f9eb7913a" />

<br/>
</br>

<img width="408" height="383" alt="image" src="https://github.com/user-attachments/assets/1ea2c847-2360-47c3-8792-2f063dd47b44" />


</br>

필요한 경우, CLI에서 selector를 통해 원하는 오브젝트를 직접 조회할 수도 있다.

<br/>

<img width="636" height="71" alt="image" src="https://github.com/user-attachments/assets/d288526e-23b3-4d4a-99ca-dacc2fa26431" />

### Annotation

Label이 오브젝트를 그룹 짓기 위함이라면, Annotation은 추가적인 정보를 더하기 위함이다. 이를 이용해, 빌드를 자동화하는 등 여러 
추가적인 기능을 구현할 수 있다.

</br>
<br/>

<img width="407" height="424" alt="image" src="https://github.com/user-attachments/assets/74dfec6b-19d0-412d-bfa4-e6deed8d978c" />

<br/>
</br>

## Taints And Tolerations

위 속성은 파드와 노드간의 관계, 즉, 파드가 어떤 노드에 위치할지에 대한 제한을 걸 수 있는 기능이다.

Taint란, 노드에 거는 제한 조건으로, pod 중, 노드에 걸린 taint에 toleration이 없다면 해당 노드에는 배치될 수 없다.
그러나 반대로, 노드에 걸린 taint를 toleration 할 수 있는 파드는, 해당 노드에 배치될 수 있다.

이를 이용하면, 특별한 자원이나 역할이 할당된 노드에, 마찬가지로 특별한 자원을 필요로 하는 파드를 집중배치할 수 있게 된다.


<img width="755" height="427" alt="image" src="https://github.com/user-attachments/assets/c0f20df5-2eb4-4bd7-803b-fef93a3bf7d4" />

### How To Set Taints on node?

```
kubectl taint nodes {node_name} key=value:{taint-effect}

taint-effect
1. NoSchedule
2. PreferNoSchedule (가능한 한 배치를 안 하지만, 도저히 없다면 스케줄을 허용한다)
3. NoExecute (기존에 존재하는 파드 중, toleration이 없다면 다른 노드로 쫒아낸다)

```

### Hot to set toleration on pods

spec.tolerations 내의 toleration 관련 내용을 아래와 같이 추가한다. 또한 중요한 점은, 어떤 node의 taint에 대하여
toleration이 있는 pod가 있어도, 이 파드가 반드시 taint가 걸린 노드에만 배치되는 것을 강제하지는 않는다는 것이다.
이를 구현하기 위해서는 node affinity를 사용하면 구현할 수 있다.

</br>


<img width="249" height="301" alt="image" src="https://github.com/user-attachments/assets/38b66a3a-1789-4f92-8e1d-e346089463e4" />

</br>
</br>

## Node Selector

파드가 특정 노드 위에 배치될 수 있도록, Pod definition file 내에 spec.nodeSelector 내에 key-value 형태로 지정할 수 있다.
그러면 스케줄러는, node의 label을 탐색하여, 위에서 설정한 key-value에 매칭되는 노드가 있으면, 거기에 파드를 배치한다.

이를 위해서는 노드에 레이블이 부착되어 있어야 하며, 이를 위해 다음과 같은 명령어를 사용할 수 있다.

```
kuybectl label nodes {node_name} <label-key> = <label-value>

```

Node Selector는 간단하고, 명료한 강력한 기능이지만, 다음과 같이 복잡한 케이스는 처리하지 못한다.

1. every node except for not small-labeld nodes
2. every node labeld with midium or large
3. .....

위와 같은 복잡한 케이스를 처리하기 위해, 쿠버네티스는 다양한 기능을 제공한다


## Node Affinity

파드와 노드를 매칭하는 과정에서 Node Selector가 제공해주지 못하는, 다양한 표현식을 Node Affinity를 이용해 구현할 수 있다. 이때 주목할 점은, operator로, in, not in, equal 등 다양한 연산자를 제공하기 때문에, 이를 통해, node-selector로는 표현하지 못하는 다양한 표현식을 사용할 수 있다.

</br>
</br>

<img width="447" height="348" alt="image" src="https://github.com/user-attachments/assets/8722f06c-1bc3-4a53-a5b3-b6b90ac5c5b5" />

</br>
</br>

만약, affinity에 매칭되는 노드가 없거나, 혹은 매칭 되는 노드가 사라지게 될 경우에는 어떻게 될까?
이에 대해서는, affinity 설정 시 사용하는 옵션에 따라 다르게 반영되며 현재는, 크게 다음과 같은 옵션을 사용할 수 있다.

사용하는 옵션에 따라서, 파드가 아예 배치되지 않을 수도 있고, 도저히 불가능한 경우 배치될 수도 있다.
또한, 현재는 파드가 실행 중, 노드의 레이블이 변경되어 더이상 affinity가 매칭되지 않는 경우에는, ignoredDuringExecution이 보여주듯 
계속 실행을 이어간다.

</br>

<img width="631" height="312" alt="image" src="https://github.com/user-attachments/assets/1175e068-2a9a-485b-b8a1-9cfae595957f" />

</br>

<img width="688" height="353" alt="image" src="https://github.com/user-attachments/assets/85ed44d0-7b1a-4054-8f13-f93f3e4fc508" />



