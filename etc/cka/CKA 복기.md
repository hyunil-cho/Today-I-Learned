1. PriorityClass 설정 및, 파드에 적용 (이때, 이미 CPU가 클러스터에 부족한 상태인데, PC를 설정해서 다른 pod를 eviction 시키고, 새로 생성한 파드가 올라가야함
2. argocd helm 설치 -> 레포 등록 및 CRD 설치(이때, helm install 시, 옵션을 주어서, 바로 설치되지 않도록 해야함)
3. Network Policy -> 서로 다른 네임스페이스에 있는 디플로이먼트 파드끼리 통신이 양방향 통신이 가능토록 설정해야 함
4. configmap 설정 -> nginx directves 설정을 통한 tls 1.2, 13을 사용할 수 있도록 하고(단순 nginx configmap에 넣으라는 값만 추가하면 됨), configmap을 immutable하게 만들어야 함. 
   -> https://kubernetes.io/docs/concepts/configuration/configmap/#configmap-immutable
5. ingress에서 gateway로 마이그레이션 및 http route 설정( 이때, 기존 인그레스에서 hostname이 바뀌는데 이거 신경서야 함) 및 TLS 설정 유지
6. cri-docker 설치 및, ip_tables 등 설정값 추가
7. kubeadm을 이용한 노드 조인 시 실패한 노드를 원상복구 시켜야 함
8. 파드를 만들고, clusterIp, nodeport로 노출
9. log만 찍는 사이드카 생성
10. cert-manager CRDS 목록 찍기(문제 내용 이해 못함 ㅠ)

# HEML

ARGOCD 헬름 차트 관련해서

1. 헬름 레포 추가(문제 상에 주어짐)
helm repo add argo https://argoproj.github.io/argo-helm
2. helm repo update
3. helm search repo argo 명령어를 통해, 필요한 차트 검색
4. helm search repo argo/argo-cd --versions 필요한 차트 중 특정 버전 검색(문제에서는 7.7.1? 버전을 명시함)
5. helm pull argo/argo-cd --version 7.7.1 헬름 다운로드

# PV rebinding

그리고, 마리아DB 관련해서도 하나 나왔어요. 시나리오는, mariadb가 있는데, 기존께 깨져서 고치는 중이다. 데이터가 유실되면 안되니, 기존의 볼륨을 이용해야 하고, 새로운 PVC를 만드는데, 이게 기존에 존재하던 볼륨과 바인딩되야 한다고 하네요. 이때, 기존에 있던 볼륨은 건들지 말라고...

기존에 있던 볼륨은 PVC-~~~라는 이름으로 보아서, 지난번에 동적으로 프로비저닝된 볼륨같은데, 여기에 바인딩을 시켜야 합니다. 그 후에는, 이미 준비된 mariadb deploy yaml에서 새로 바인딩된 pvc 마운트하면 됩니다


## Chatgpt -> 이미 존재하는 PV에 PVC Binding은 가능하다

이에 대해서 chatgpt한테 물어보니까, 다음 조건을 만족하면 된다고 하네요

1. 기존 PV가 어떤 storageClassName을 가지고 있나요? 새 PVC도 같은 storageClassName을 명시해야 매칭돼요.

만약 PV의 storageClassName: ""(즉, none)이라면 PVC에서도 storageClassName: ""로 맞춰야 해요.

2. Access Modes가 동일
3. PVC가 request 하는 용량이 PV보다 작아야 함
4. ReclaimPolicy가 동일해야 함. 
5. 기존 PV의 상태가 Released 또는 Available이면 PVC와 다시 바인딩 가능. 다만 Released 상태라면 PVC와 자동 바인딩이 안 될 수 있어서, 수동으로 PVC와 PV를 연결해야 해요.

만약 수동으로 해야 한다면, PVC 내에 volumeName을 명시적으로 지정해야 함




