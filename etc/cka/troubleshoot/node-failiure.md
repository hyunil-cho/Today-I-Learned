# 노드가 문제인 경우

```
kubectl get nodes 명령어를 통해, 노드의 상태를 파악한다.
kubectl describe node {node-name{ <-=- 이 명령어를 통해, 노드에 발생한 이벤트를 확인할  수 있다.

만약 노드가 클러스터에서 떨어진다면, 위 명령어를 쳤을 때, unknown이라는 상태 값을 확인할 수 있다.

```

만약, 노드에 문제가 있다면, 디스크 상태나, kubelet 상태 등을 OS 명령어를 통해 확인한다. journalctl, crictl 등
이외 인증 문제라면, client certificate를 확인하여, expired 여부나, right CA에 의하여 signed 된 것인지 확인한다.
또한, client group이 잘 되었는지도 확인하는 것이 중요하다. - 이게 맞지 않으면, authenrication은 성공해도, authorization에서 실패한다.
