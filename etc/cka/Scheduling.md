# Scheduling 

## Manual scheduling

기본적으로 pod defiition file 생성 시, 스케줄러는 파드를 할당할 수 있는 노드를 검사하고, 선정 알고리즘에 따라 적합한 노드를 생성한다.
이후, 파드의 spec.nodeName field에 선정된 Node Name을 스케줄러가 생성 및 삽입하면 파드가 해당 노드에 스케줄링 된다.

만약, 스케줄러가 없이 수동으로 스케줄링해야 한다면, 파드 생성 시, spec.nodeName 프로퍼티에 노드 이름을 지정하여 스케줄링 할 수 있다.

<br/>


<img width="324" height="315" alt="image" src="https://github.com/user-attachments/assets/41a82a51-7a24-4fd8-8419-1ea90778409c" />


그러나, 이미 생성된 파드의 경우에는, spec.nodeName을 변경할 수 없기 때문에, 이 경우에는 Binding Object를 생성 후, binding api를 호출해줄 수 있다.

<img width="941" height="502" alt="image" src="https://github.com/user-attachments/assets/5172c578-3e08-4651-96f8-8e761ed208eb" />
