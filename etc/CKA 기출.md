1. deployment 레플리카의 resource request와 limit을 적절히 조절하여 레플리카를 3개를 띄워야 한다. 이때, 파드의 오버헤드를 고려하여 자원을 할당해야 한다. 이때, InitContainer와 main Container의 request, limit은 동일해야 함
```
kubectl describe node {nodeName} 명령어를 통해, 현재 노드의 allocatable 리소스에서 이미 할당된 
리퀘스트 자원을 뺀 이후, 나머지 자원을 사용하여야 함.

예를 들어, 전체 사용 가능한 자원이 CPU 1000, 메모리 100Mi라면, 이 중, 10%는 오버헤드를 위해 남겨두고, 나머지 자원을 파드 개수만큼 나누어 파드의 request를 설정, limit은 설정한 자원에 이전에 남겨둔 10%를 마찬가지로 파드 개수만큼 나누어 계산한 후, limit에 request에 설정한 값에 추가하여 설정!

이때 중요한 점은, initContainer와 container에 주는 자원이 동일해야 한다는 점! 또한, InitContainer는 작업을 완료한 이후, 내려가기 때문에, 자원을 3등분 할 것을 6등분 할 필요는 없음

```

2. PriorityClass 설정 및 적용하라. 이때, 적용된 파드는 pending 상태가 아닌 running 상태이어야 한다

```
설정된 PC 중, 사용자가 정의한 PC의 값보다 1 작은 PC를 생성하고, 이를 기존에 존재하는 deployment에 적용해야 한다.
이때, 중요한 것은, pc가 적용된 파드는 우선순위를 가지고 running 상태로 변경되어야 한다.

문제 조건 상, 메모리가 부족한데, deployment에 pc를 적용함으로써, 스케줄링 상에서 우위를 점하여 먼저 노드에 스케줄링되어야 하는게 포인트!!

```
   
