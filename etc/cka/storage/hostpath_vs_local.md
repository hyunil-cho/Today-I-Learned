🧱 1️⃣ 공통점
____________
| 항목 | 설명 |
____________
저장 위치	둘 다 노드 로컬 디스크를 사용 (즉, Pod가 스케줄된 노드 안의 파일시스템에 저장됨)
데이터 지속성	Pod가 재시작해도 같은 노드라면 데이터 유지 가능
다른 노드로 이동 시	다른 노드에는 데이터 없음 → “Node-local” 특성
성능	네트워크 스토리지보다 빠름 (로컬 I/O이므로)

🪵 2️⃣ 차이점 요약표
| 항목	| HostPath Volume |	Local PersistentVolume (local volume)
|정의 위치 | 	Pod spec 안에서 직접 지정	| PersistentVolume 리소스로 정의|
|용도	| 테스트, 디버깅, 임시 로컬 접근용 |	실사용용 “로컬 퍼시스턴트 스토리지”|
|스케줄링| 인식	없음 (임의의 노드에 붙을 수 있음)	|스케줄러가 특정 노드에 종속된 볼륨임을 인식함 |
|안전성|	위험함 (호스트 파일시스템을 직접 건드림) |	안전함 (노드별 PV로 관리, 볼륨 바인딩 관리 가능)|
|PVC 바인딩 가능	|❌ (불가능)|	✅ (가능, PVC로 연결)
|노드 고정성 (Node Affinity)|	없음	|있음 (nodeAffinity로 해당 노드에 고정)|
|프로덕션 적합성	|비권장	| 권장 (로컬 디스크 기반 스토리지 구축 시)

```
apiVersion: v1
kind: Pod
metadata:
  name: test-hostpath
spec:
  containers:
  - name: app
    image: busybox
    volumeMounts:
    - name: host-vol
      mountPath: /data
  volumes:
  - name: host-vol
    hostPath:
      path: /var/tmp/test
      type: Directory

```
```
apiVersion: v1
kind: PersistentVolume
metadata:
  name: local-pv
spec:
  capacity:
    storage: 10Gi
  volumeMode: Filesystem
  accessModes:
    - ReadWriteOnce
  persistentVolumeReclaimPolicy: Delete
  storageClassName: local-storage
  local:
    path: /mnt/disks/ssd1
  nodeAffinity:
    required:
      nodeSelectorTerms:
      - matchExpressions:
        - key: kubernetes.io/hostname
          operator: In
          values:
            - worker-node-1
```
