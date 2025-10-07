# Kubernetes Pod CIDR

## 1️⃣ Pod CIDR란?
- **Pod CIDR**는 클러스터 내 **각 Pod에 할당될 IP 주소 범위**를 정의한 것입니다.
- 쿠버네티스에서 Pod는 **각각 고유한 IP**를 가지며, 서로 직접 통신이 가능하도록 설계되어 있습니다.
- 예시:

10.244.0.0/16 # 전체 클러스터의 Pod IP 풀
10.244.1.0/24 # 특정 Node에 할당될 Pod IP 풀


---

## 2️⃣ 설정하는 이유

1. **Pod 간 통신 보장**
 - 쿠버네티스는 “모든 Pod는 서로 접근 가능”을 기본 원칙으로 합니다.
 - CIDR이 없으면 어떤 IP를 Pod에 줘야 할지 결정할 수 없습니다.

2. **Node 간 IP 충돌 방지**
 - 각 Node에 **서브넷**을 나누어 Pod IP를 할당합니다.
 - 예: Node1 → 10.244.1.0/24, Node2 → 10.244.2.0/24
 - 이렇게 하면 **같은 CIDR 내 IP 중복**이 발생하지 않습니다.

3. **CNI(Container Network Interface) 플러그인 지원**
 - Flannel, Calico, Weave 등 CNI는 **Pod IP 할당 범위**를 기반으로 통신 네트워크를 구성합니다.
 - Pod CIDR 설정 없이는 CNI가 Pod IP를 관리할 수 없습니다.

4. **라우팅 / 네트워크 정책 적용**
 - Pod CIDR은 Node 라우팅 테이블 생성과 NetworkPolicy 적용 기준이 됩니다.
 - 예: 10.244.1.0/24 → Node1 내부 라우팅, 정책 적용

---

## 3️⃣ Master / Controller 역할
- kube-controller-manager는 Node가 추가될 때마다 **Pod CIDR 블록을 Node에 할당**합니다.
- 예를 들어, Flannel을 쓰면 `--allocate-node-cidrs=true` 옵션으로 Node별 CIDR을 자동 할당합니다.
