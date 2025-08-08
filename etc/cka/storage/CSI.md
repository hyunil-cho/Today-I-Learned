# Container Storage Interface : CSI

쿠버네티스 초기 버전에는 오직 도커만이 컨테이너 런타임으로써만 사용할 수 있었다. 그러나, 컨테이너 런타임 프로그램이 여럿 개발되면서, CRI가 만들어진 것 처럼,
쿠버네티스가 여러 스토리지 엔진과 편하고, 간단하게 통합될 수 있도록 CSI(Container Storage Intefercae)가 정의됐다.

CSI는 쿠버네티스 뿐만 아니라, 다른 리소스 매니저도 모두 사용할 수 있는 standard로, Volume이 제공해야 할 RPC call을 정의하고 있다.
그렇기에, 이를 구현한 Volume이라면, 어떤 구현체라도 쿠버네티스와 같이 CSI를 지원하는 오케스트레이션 툴과 협업이 가능하다.

<img width="1040" height="571" alt="image" src="https://github.com/user-attachments/assets/ce7bea13-d9cd-490c-9fc3-c9036b21c518" />


</br>
</br>

<img width="1790" height="836" alt="image" src="https://github.com/user-attachments/assets/fe322642-5cea-4854-9202-91990ac9b851" />
