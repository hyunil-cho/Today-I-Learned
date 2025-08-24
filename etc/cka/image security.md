# Image Security

## Image Naming Convention

{registry}/{user/account(이미지 관리자)}/{image/repository}:version

## private image registry

private repository를 만들고 관리하는 경우, 이미지를 풀하기 위해서는 인증 과정을 거쳐야 한다.
이를 위해, 이미지를 풀하기 위해서는 인증 정보를 함께 넘겨주어야 한다.

이를 위해, 예를 들어, kubectl create secret docker-registry 이름 --options 를 통해, 도커 저장소 인증 시크릿을 만들고
파드 정의 내에, 이를 추가할 수 있다.
이때, spec.imagePullSecrets: 필드에 추가한다.
