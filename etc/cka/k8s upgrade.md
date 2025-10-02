# 문제 상황

1. 워커 노드의 kubectl & kubelet의 버전이 control plane의 버전보다 마이너 레벨이 한 단계 낮음
2. 워커 노드가 cluster에 조인하지 않고 있음


# 해결 순서

1. control plane의 버전 확인 -> k get node -> 컨트롤 플레인의 kubernetes 버전 확
2. worker node의 kubeadm, kubelet, kubectl 버전 확인
```
kubeadm version
kubectl version
kubelet --version
```
3. https://v1-33.docs.kubernetes.io/docs/tasks/administer-cluster/kubeadm/kubeadm-upgrade/ 문서를 참고하여,
   설치하고자 하는 버전을 확인 및, 설치. 이때, kubelet은 반드시 재기동을 해주어야 함!

4. 설치가 완료되면, 마스터로 가서 아래 명령어를 수행하면, kubeadm join을 위한 명령어를 획득할 수 있음.
```
kubeadm token create --print-join-command
```

5. 4번에서 획득한 명령어를 워커노드에서 수행 및 kubectl get node를 통한 결과 확인!

# 시사점
