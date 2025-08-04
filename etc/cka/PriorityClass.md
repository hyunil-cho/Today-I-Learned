<img width="424" height="192" alt="image" src="https://github.com/user-attachments/assets/7a7055f4-3ab0-406c-8b31-b8afabf5f6f0" /># Priorities

쿠버네티스 클러스터를 운영하는데 있어, 파드의 타입이나 역할에 따라서, 필수불가결하게 다른 것보다도 우선해서 돌아야 하는 파드나 서비스가 있을 수 있다
이를 해결하기 위해서 쿠버네티스에서는 Priorities를 지정하고 스케줄 시, 클러스터 내에 자원이 부족하여
파드 스케줄링이 실패할 경우, 더 낮은 레벨의 Priorities를 가지는 파드를 종료시킴으로써, 더 높은 레벨의 파드를 
우선해서 실행할 수 있도록 보장한다.

Priorities 오브젝트는 네임스페이스가 붙지 않기 때문에, 생성 후, 어떤 네임스페이스 내에 있는 파드에도 적용이 가능하다

이는 10억부터 -Integer.maxVALUE까지 설정이 가능하며, 이 사이에 값을 파드에 적용이 가능하며,
높을 수록 높은 우선순위를 가진다.

또한, 20억~10억1까지는 시스템 컴포넌트에 지정되어, 시스템 유지에 필요한 컴포넌트가 우선해서 스케줄링될 수 있도록 한다.

</br>
</br>
<img width="905" height="406" alt="image" src="https://github.com/user-attachments/assets/1088a701-54d3-4df1-a839-fd54eda6d015" />
</br>
</br>

이때, PriorityClass를 지정하지 않으면 기본은 0이며 변경하기 위해서는 PriorityClass를 생성한다.
이때, 아래와 같이, PriorityClass에 설정을 추가하면, 별도로 PriorityClass가 설정되지 않은
모든 파드에 적용된다. 단 이 경우, 파드에는 단 하나의 PriorityClass만 설정되기 때문에, 충돌이 나지 않도록 한다.

```
globalDefault: true
```

또한, 만약 더 높은 우선순위를 가진 파드가 생성될 때, 자원이 없을 경우에는 어떻게 될까? 이 경우, PreemptionPolicy의 설정 값에 따라 행동이 결정된다.

만약, PreemptLowerPriority(default)로 설정된 경우, 더 적은 우선순위의 파드를 evict한다. 그러나, Never로 설정되어 있다면, evict 하지 않으며, 자원이 날 때까지 대기한다.

<img width="424" height="192" alt="image" src="https://github.com/user-attachments/assets/f0870d70-8e26-4b9a-bd14-39864a916ab8" />

