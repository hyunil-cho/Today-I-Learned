Kubernetes Authorization: ABAC(Attribute-Based Access Control)
개요

ABAC는 Kubernetes의 초창기 권한 제어 방식 중 하나로, 사용자, 그룹, 리소스, 동작 등에 대한 **속성(Attribute)**을 기반으로 접근을 허용하거나 거부하는 방식이다.

RBAC(Role-Based Access Control)이나 Webhook 기반 Authorization이 등장하기 전 사용되었으며, 현재는 비권장(deprecated) 상태다.

주로 실험적이거나 제한된 환경에서만 활용된다.

동작 원리

Kubernetes API Server는 **정책 파일(Policy File)**을 JSON 형식으로 읽어들인다.

이 파일에는 각 요청을 허용할 조건(속성)이 정의되어 있다.

API 요청이 들어오면, 요청의 속성과 정책 파일의 규칙을 비교하여 허용(allow) 또는 **거부(deny)**를 결정한다.

정책 파일 예시

policy.jsonl (JSON Lines 형식)

{"apiVersion": "abac.authorization.kubernetes.io/v1beta1", "kind": "Policy", "spec": {"user":"alice", "namespace":"dev", "resource":"pods", "readonly": true}}
{"apiVersion": "abac.authorization.kubernetes.io/v1beta1", "kind": "Policy", "spec": {"group":"dev-team", "namespace":"*", "resource":"*", "readonly": false}}


첫 번째 규칙: alice 사용자는 dev 네임스페이스의 pods 리소스에 대해서 읽기 전용 권한만 가진다.

두 번째 규칙: dev-team 그룹은 모든 네임스페이스와 리소스에 대해 전체 권한을 가진다.

주요 속성

user: 요청한 사용자 이름

group: 요청자가 속한 그룹

namespace: 접근하려는 리소스의 네임스페이스

resource: 리소스 종류 (예: pods, services)

readonly: 읽기 전용 여부 (true = GET/HEAD만 가능, false = 모든 동작 가능)

장점

유연한 속성 기반 제어 가능

사용자/그룹/리소스 단위의 세밀한 정책 작성 가능

단점

관리 어려움: JSON 정책 파일을 직접 관리해야 함

정책 실수 위험: 규칙이 많아질수록 충돌 가능성이 커짐

확장성 부족: 대규모 클러스터에서 유지보수 힘듦

Deprecated: Kubernetes 최신 버전에서는 더 이상 권장되지 않음 (RBAC 사용 권장)

대안

현재 Kubernetes에서는 **RBAC(Role-Based Access Control)**가 기본이자 권장 방식이다.

RBAC은 Kubernetes 리소스로 관리할 수 있으며, 동적으로 변경 가능하다.

Webhook Authorization과 결합해 외부 시스템과 연동할 수도 있다.
