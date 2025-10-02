# 문제 상황

kube-apiserver의 crt를 확인하고, 이를 통해 인증기간 만료 시간을 구하라

# 해결

1. kubeadm으로 설치 시, control plane 관련 인증서는 /etc/kubernetes/pki 내부에 존재함
2. 이 중, kube-apiserver.crt 파일은 api server가 사용하는 인증서임
3. 기본적으로 인증서는 x509 형식으로 만들어져 있기 때문에 openssl x509 -in {file} -text 명령어를 통해 확인 가능
4. 이외에도, kubeadm certs 명령어를 통해, 컴포넌트의 certification 유효기간 및 재갱신 등 다양한 작업을 수행 가능
