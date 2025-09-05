[https://kubernetes.io/docs/reference/access-authn-authz/certificate-signing-requests/]

# Certificates and Certificate Signing Requests

>Kubernetes certificate and trust bundle APIs enable automation of X.509 credential provisioning by providing a programmatic interface for clients of the Kubernetes API to request and obtain X.509 certificates from a Certificate Authority (CA).

#  CertificateSigningRequest (CSR)

CSR resource를 통해, 사용자는 cA로부터 certificate의 sign을 요청할 수 있으며, 이는 거절되거나, 통과될 수 있다.

# spec

1. he CertificateSigningRequest object includes a PEM-encoded PKCS#10 signing request in the spec.request field
2. field. The CertificateSigningRequest denotes the signer (the recipient that the request is being made to) using the spec.signerName field. Note that spec.signerName is a required key after API version certificates.k8s.io/v1
3. In Kubernetes v1.22 and later, clients may optionally set the spec.expirationSeconds field to request a particular lifetime for the issued certificate

# after request

경우에 따라서, controller에 의하여 자동으로 처리되거나, 혹은 다음 명령어를 통해 직접적으로 요청을 승인해야만 
```
kubectl certificate approve
```

이후, 컨트롤러는 일련의 벨리데이션을 진행한 이후, 인증서를 생성하게 되며, 생성된 CSR내의 status.certificate 필드에 인증서를 저장함.
이때, 해당 필드는 비어있거나, 혹은 PEM 형태로 인코딩된 인증서가 저장되어 있음. 이후, 클라이언트는 status.certificate 필드에서 PEM 형태의
certificate를 복사하여 사용하면 

# GC

CSR은 상태(허용, 거절, 요청 등)에 따라, TTL을 가지며, 이 시간이 지나면 컨트롤러는 해당 CSR을 클러스터 내부에서 삭제함


# 권한!

CSR을 요청하기 위해서는 cluster role로 CSR을 생설 및 확인할 수 있는 권한을 바인딩해야 함. 또한 CSR을 사인하기 위한 권한이 있어야만 CSR을 사인할 수 있으며, 이도 마찬가지로 cluster role 바인딩이 필요

```
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: csr-creator
rules:
- apiGroups:
  - certificates.k8s.io
  resources:
  - certificatesigningrequests
  verbs:
  - create
  - get
  - list
  - watch
```

