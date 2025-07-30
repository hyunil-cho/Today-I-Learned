# Resource

효율적으로 파드를 노드에 배치하기 위하여, 파드가 필요로 하는 자원과, 노드가 현재 할당가능한 자원을 비교하여 스케줄링을 하는 것이 가능하다.
이를 위해, 파드 내에 다음과 같이 spec.containers.reousrces를 통해, 해당 파드가 필요로 하는 최소한의 CPU 및 메모리 자원을 명시할 수 있다.

노드가 클러스터에 합류하게 되면, 노드에 설치된 kubelet은 노드의 시스템 정보를 토대로, 논리코어와 사용 가능한 메모리를 파악해둔다.
이를 기반으로 pod가 배치될 때, 배치된 파드의 resource 요청과, 현재 가용 자원을 비교하여, 새로운 파드가 배치될 때, 가용한 리소스가 
해당 파드를 돌리기에 부족하다고 판단한다면 스케줄링을 거부하게 된다. 이후 파드가 어떤 노드에도 배치되지 못한다면, pending 상태가 된다.

</br>

<img width="552" height="435" alt="image" src="https://github.com/user-attachments/assets/d987625d-c87b-4c1c-9af8-e851bb649f82" />

</br>

## What does CPU mean?

resource에 설정되는 CPU 개수는 환경에 따라 여러 의미로 사용되며, 온프레미스 환경에서는 hyperthread 개수와 동일하다고 볼 수 있다.
이때, 100m과 같이 m을 붙일 수 있는데, 이때 m은 밀리코어의 약작로, 1000m은 1과 동일하며, 하나의 논리 코어 수를 의미한다.

</br>

<img width="489" height="416" alt="image" src="https://github.com/user-attachments/assets/ace49aef-1793-4622-bfcc-a3d9f2f23a74" />
<img width="975" height="431" alt="image" src="https://github.com/user-attachments/assets/5e637695-1dce-481f-9052-45deeeb8ec8f" />


# Limit

노드에 배치된 파드가 사용할 수 있는 자원의 한계를 정의한다. limit을 정의해두지 않으면, pod는 배치된 노드 내의 자원을 한도 없이 다 사용할 수도 있다.

</br>

<img width="1031" height="526" alt="image" src="https://github.com/user-attachments/assets/9d0b4554-615b-48f4-a907-748d4eb2df56" />

</br>

만약, 파드가 limit에 설정한 자원 이상을 사용하는 경우는 어떻게 되는가?
CPU의 경우, throttle을 통해, CPU 사용을 제한함으로써 LIMIT 설정 이상을 사용하지 못하도록 제한한다.
하지만, 메모리의 경우, LIMIT을 넘어서게 되면, OOM 킬러에 의하여 해당 pod는 terminated 된다.
