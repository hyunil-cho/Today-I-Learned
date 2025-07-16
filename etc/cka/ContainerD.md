#Containerd란?

containerd는 runC를 이용해 Linux 커널의 네임스페이스(namespace), cgroups 등의 기능을 활용하여 컨테이너의 생성, 실행, 삭제 등의 생명주기를 관리하는 고수준의 컨테이너 런타임입니다.
원래는 Docker의 핵심 구성요소 중 하나로 개발되었으나, 2017년 독립 프로젝트로 분리되어 CNCF(Cloud Native Computing Foundation)의 관리 하에 오픈소스로 운영되고 있습니다. 🔧

💡 containerd는 자체적으로 gRPC API를 제공하며, 이미지 관리(풀, 푸시), 네트워크, 볼륨, 로그 등 컨테이너 실행에 필요한 기능을 포괄적으로 제공합니다. 그러나 UI나 CLI는 제공하지 않으며, 상위 도구(Docker, CRI-O, Kubernetes 등)가 이를 래핑합니다.

# Docker와 Kubernetes의 관계
Kubernetes가 처음 등장하던 시기, 컨테이너화의 대표적인 도구는 Docker였습니다. 이에 Kubernetes는 컨테이너 런타임으로 Docker를 지원했으며, 내부적으로 Docker를 통해 컨테이너를 생성 및 관리했습니다.

하지만 Docker는 단순한 런타임이 아니라 다음과 같은 다양한 기능을 포함하고 있습니다:

- Docker CLI 및 데몬
- 이미지 빌드(BuildKit)
- 인증/인가 기능
- 이미지 저장소 통합
- 로그, 네트워크, 볼륨 관리 등

이처럼 Docker는 모놀리식(monolithic) 구조를 갖고 있어, Kubernetes가 필요로 하는 순수 런타임 기능에 비해 무겁고 복잡했습니다. 🔧

💡 Kubernetes는 이러한 의존성을 줄이기 위해 CRI(Container Runtime Interface) 를 정의하였고, 이후부터는 CRI만 구현하면 어떤 런타임이든 Kubernetes에서 사용할 수 있게 되었습니다.

즉, Kubernetes는 더 이상 Docker에 직접 의존하지 않으며, containerd, CRI-O 등 경량화된 런타임을 주로 사용하게 되었습니다.

Docker 지원 종료 이슈 (Dockershim 제거)
Kubernetes는 v1.20부터 Docker에 대한 직접적인 지원을 중단(deprecated) 한다고 발표했고, v1.24부터는 Dockershim(중간 어댑터) 을 완전히 제거했습니다.
이는 Kubernetes가 Docker를 완전히 버린다는 의미는 아니며, Docker를 통해 생성된 이미지는 여전히 사용 가능합니다.

💡 요약하자면:

- Kubernetes는 Docker → containerd 또는 CRI-O 등의 경량화된 런타임으로 전환
- Docker는 여전히 개발, 테스트, 로컬 환경에서 많이 사용됨

# containerd와 runC의 관계
runC: 리눅스 컨테이너 실행을 위한 가장 하위 레벨의 표준 런타임 (OCI 호환)
containerd: runC를 이용하여 컨테이너의 라이프사이클을 관리하는 고수준 런타임
Docker: containerd를 포함하여 CLI, 빌더, 이미지 툴, 데몬 등을 묶은 전체 플랫폼
