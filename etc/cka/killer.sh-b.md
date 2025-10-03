1. SVC, POD FQDN DNS 접근 방법
https://kubernetes.io/docs/concepts/services-networking/dns-pod-service/
2. kubelet key 관련 저장 장소 - kubeadm 설치 기준
/var/lib/kubelet/pki/

client cert -> kubelet-client-current.pem
server cert -> kubelet.crt

## PEM과 CERT 차이?
```
-----BEGIN CERTIFICATE-----
MIIDXTCCAkWgAwIBAgIJAJcQf....
... (Base64 인코딩된 내용) ...
x+7mSg6fY6w2O2d93Q==
-----END CERTIFICATE-----
```
위와 같이, 인증서 파일은 헤더(---BEGIN---)로 시작해 암호화가 된 실제 인증서 파일, 그리고 푸터(---END---)로 이루어져 있음. 이는 .pem, .crt, .key에 모두 적용되는 것이며, 차이점은, 내부에 키만 있는지, 혹은 인증서만 있는지, 혹은 다 있는지 등에 따라 이름이 관습적으로 바뀔 뿐, 형태 적으로는 차이가 없음
