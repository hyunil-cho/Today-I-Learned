# BackUp

시스템 가용성을 위해서, 다음과 같은 데이터를 백업해둘 수 있음

1. Resource Configuration
  2. yml과 같은 파일은 가능하면, Github 같은 레포지토리에 저장하고, 백업을 염두에 두어야 한다.
  3. API 서버, 혹은 kubectl을 통해, 현재 있는 모든 리소스를 저장 가능
  4. kubectl get all --all-namespace -o yaml > all-deploy-servies.yaml
5. ETCD Cluster
  6. 클러스터 정보, 노드 정보 등 클러스터 내에서 생성된 모든 정보가 저장됨
  7. etcd 서버 설정 정보 중, 데이터가 저장되는 디렉터리를 백업한다.
  8. 혹은, 빌트인 기능인 스냅샷 API를 사용할 수 있다.
  9. 생성된 스냅샷을 이용해, etcd 재실행 시, 스냅샷 주소를 지정하면, 새로운 클러스터 정보를 생성한다.
10. Persistent Volumnes

