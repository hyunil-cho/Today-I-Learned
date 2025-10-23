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

3. Network Policy 중 가장 not-over-permissive한 Network Policy 적용

```
Backend 네임스페이스와, FrontEnd 네임스페이스 위치한 디플로이먼트의 파드끼리 통신이 가능토록 해야 함.
이때, 각 네임스페이스에는 default networkpolicy가 적용되어 있으며, 이는 deny all임.

즉, 기본적으로는 두 네임스페이스간의 통신은 불가한 상태에서, 예시로 주어진 network policy 중 하나를 적용해야 함

1번의 경우에는, backend namespace의 파드를 대상으로 적용되며, ingress.from 조건에 namespaceSelector가 걸려 있으며, frontend 네임스페이스에 속하는 파드가 접속하는 것을 허용하고 있음.

2번은, 마찬가지로 backend namespace pod를 대상으로 하며, ingress.from에 namespaceSelector와 podSelector가 and 조건으로 묶여있으며, namespaceSelector는 frontend를 가리키고, podSelector는 그 중에서도 frontend deployment에 속하는 파드를 가르키고 있음.

3번은 전혀 다른 파드를 가르키고 있기 때문에, 고려 대상이 아니었음.

이 중, 2번은 1번도 더 엄격하게 조건이 걸려있음. namespace 뿐만 아니라 podSelector도 and로 묶여있어, 조건에 부합하는 조건(not-overly-permissive)은, namespaceSelector, podSelector 두 개가 and로 묶여있는 2번임

```

4. 트러블 슈팅 - 쿠버 업그레이드 이후 깨진 클러스터 복구

```

트러블 슈팅의 경우, 단계별로 천천히 어디가 문제인지 파악하는 것이 가장 중요함.
1. kubectl get node 명령어를 시도, api 서버와 통신이 안되어 명령어가 실패함
2. systemctl status kubelet을 명령어를 통해 kubelet가 running 상태임을 확인
3. journalctl -u kubelet -r 명령어를 통해, 가장 최근 kubelet 로그를 확인 -> api 서버와 통신이 안되고 있음
4. crictl ps로 컨테이너를 확인해보았으나, api-server 컨테이너가 떠있지 않았음.
5. crictl ps -a 명령어를 통해, api-server 컨테이너가 내려가 있는 것을 확인 및
6. 죽은 컨테이너의 로그를 확인해보니 etcd-서버와 통신이 실패함을 확인
7. etcd 서버의 listen 주소를 확인해보니, api 서버가 보고 있는 주소가 잘못된 것을 확인
8. api 서버 설정 변경을 통해, ETCD 주소를 알맞게 변경하고, kubelet을 재실행
   -> api-server는 kubelet을 통해 static-pod로 떠있었기 때문에, kubelet을 재시작하여 pod를 다시 실행해주어야 함
```

5. Ingress -> Gateway 마이그레이션

```

기존에 존재하는 Ingress를 살펴보면, TLS가 설정되어 있고, / 경로로 요청이 들어오면 이미 존재하는 백엔드로 라우팅 중이다.

이에, Gateway에서 443포트에서 리스닝중인 Listener를 설정하고, 적절하게 TLS를 설정해준다. 이때, TLS 인증서는 시크릿에 저장되어 있기 때문에, 문서를 잘 읽고 그대로 적용만 해주면 된다.

---
spec:
  gatewayClassName: nginx
  listeners:
    - name: https
      protocol: HTTPS
      port: 443
      hostname: example.com
      tls:
        mode: Terminate
        certificateRefs:
          - kind: Secret
            name: example-com-tls
---

또한, HttpRoute를 설정하고, '/' 경로에 대하여 동일한 백엔드를 보도록 설정한다.

마지막으로, 두 가지 주의점이 있는데, 먼저, 기존 Ingress와 Gateway는 호스트명이 다르다! 이 부분은 꼭 체크해야 한다.
두 번째로, 모든 마이그레이션이 끝나면 존재하는 Ignress resource는 지워주어야 한다.

```
