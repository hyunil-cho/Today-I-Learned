# 쿠버네티스(Kubernetes) 버전은 무엇을 기준으로 할까?

쿠버네티스 클러스터는 여러 구성요소(`kube-apiserver`, `controller-manager`, `scheduler`, `kubelet`, `kubectl`)로 이루어져 있습니다.  
그런데 이 컴포넌트들은 항상 동일한 버전을 가질 필요가 없습니다.  
그렇다면 일반적으로 “이 클러스터는 Kubernetes 1.30이다”라고 말할 때, **무엇을 기준으로 버전을 이야기할까요?**

---

## ✅ 결론: `kube-apiserver` 버전이 기준이다

일반적으로 쿠버네티스 버전을 말할 때는  
**API 서버(`kube-apiserver`)의 버전**을 기준으로 합니다.

### 왜 API 서버를 기준으로 할까?

1. **클러스터의 진입점 역할**
   - `kube-apiserver`는 모든 컴포넌트(`controller-manager`, `scheduler`, `kubelet` 등)와  
     클라이언트(`kubectl`)가 통신하는 중앙 인터페이스입니다.
   - 즉, 쿠버네티스 클러스터를 외부에서 바라봤을 때 **"대표 버전"** 은 API 서버가 제공합니다.

2. **버전 스큐(Version Skew) 정책 때문**
   - 쿠버네티스는 각 구성요소 간 버전 차이를 허용하지만,  
     기준이 되는 것은 항상 **API 서버**입니다.

---

## 🔍 Version Skew 정책 요약

| 구성요소 | API 서버와의 허용 버전 차이 |
|-----------|-----------------------------|
| `kube-controller-manager`, `kube-scheduler` | **최대 1 마이너 버전 낮을 수 있음** |
| `kubelet` | **최대 1 마이너 버전 낮을 수 있음** |
| `kubectl` | **API 서버와 ±1 마이너 버전 허용** |

> 📘 출처: [Kubernetes Version Skew Policy](https://kubernetes.io/docs/setup/release/version-skew-policy/)

즉, **API 서버가 항상 중심이 되어야** 하며  
다른 컴포넌트들은 그 버전에 맞춰 “호환 가능한 범위” 내에서 동작하도록 관리됩니다.


## 💡 참고 명령어

```bash
kubectl version --short
