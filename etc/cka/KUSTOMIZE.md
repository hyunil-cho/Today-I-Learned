Kustomize 정리
1. 개요

Kustomize는 Kubernetes 리소스 YAML을 템플릿 없이 커스터마이징할 수 있는 도구이다.

kubectl에 내장되어 있어 별도의 설치 없이도 사용할 수 있다 (kubectl apply -k).

공통 베이스(Base)를 만들고, 오버레이(Overlay)를 통해 환경별(dev, stage, prod 등) 차이를 관리하는 방식으로 사용한다.

2. 주요 특징

템플릿 불필요

Helm처럼 Go 템플릿을 쓰지 않고, 순수 YAML을 기반으로 한다.

Overlay 구조

공통 리소스를 Base로 정의하고, 환경별 차이는 Patch로 관리한다.

Declarative 관리

전부 YAML 선언으로 구성 → GitOps와 잘 어울림.

kubectl 통합

kubectl apply -k ./overlays/prod 처럼 바로 적용 가능.

3. 디렉토리 구조 예시
my-app/
├── base
│   ├── deployment.yaml
│   ├── service.yaml
│   └── kustomization.yaml
└── overlays
    ├── dev
    │   └── kustomization.yaml
    └── prod
        └── kustomization.yaml


base: 공통 리소스 정의 (Deployment, Service 등)

overlays: 환경별 수정 (예: replicas 수, resource limit, config 차이 등)

4. kustomization.yaml 예시
Base
# base/kustomization.yaml
resources:
  - deployment.yaml
  - service.yaml

Overlay (예: dev)
# overlays/dev/kustomization.yaml
resources:
  - ../../base

patches:
  - target:
      kind: Deployment
      name: my-app
    patch: |-
      - op: replace
        path: /spec/replicas
        value: 1

Overlay (예: prod)
# overlays/prod/kustomization.yaml
resources:
  - ../../base

patches:
  - target:
      kind: Deployment
      name: my-app
    patch: |-
      - op: replace
        path: /spec/replicas
        value: 5

5. 명령어

리소스 생성/적용

kubectl apply -k overlays/dev


리소스 확인

kubectl kustomize overlays/prod

6. 활용 사례

환경별 설정 분리: Dev/Prod에서 replica 수, image 태그, ConfigMap 등을 다르게 적용.

공통 관리: Base 디렉토리를 표준으로 잡아 여러 팀이 재사용.

GitOps: ArgoCD, FluxCD 등과 함께 사용 → 선언적 배포 파이프라인 구축.

7. 장단점

✅ 장점

템플릿 엔진 불필요 → YAML 그대로 유지

환경별 설정 분리 용이

kubectl에 내장되어 사용 편리

⚠️ 단점

Helm처럼 패키징/배포 생태계 부족

복잡한 로직 처리에는 한계 있음
