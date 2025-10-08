# volumeAttributes

> A VolumeAttributesClass provides a way for administrators to describe the mutable "classes" of storage they offer. Different classes might map to different quality-of-service levels.

## GA as of 

FEATURE STATE: Kubernetes v1.34 [stable] (enabled by default: true)

## Example

```
apiVersion: storage.k8s.io/v1
kind: VolumeAttributesClass
metadata:
  name: silver
driverName: pd.csi.storage.gke.io
parameters:
  provisioned-iops: "3000"
  provisioned-throughput: "50"
---

apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: test-pv-claim
spec:
  …
  volumeAttributesClassName: silver
  …

```

## WHY?

Kubernetes에서 StorageClass + CSI volumeAttributes + PV/PVC 구조는말 그대로 설정을 재사용하고 추상화하기 위한 구조.

동적 프로비저닝 시, CSI 별 제공하는 옵션을 재사용하도록 함으로써, 설정을 공통화하고, 중복을 제거하도록 하기 위함!
