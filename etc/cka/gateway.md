# Gateway API

> Gateway API is a family of API kinds that provide dynamic infrastructure provisioning and advanced traffic routing.

# Gateway API has three stable API kinds:

1. GatewayClass: Defines a set of gateways with common configuration and managed by a controller that implements the class.
2. Gateway: Defines an instance of traffic handling infrastructure, such as cloud load balancer.
3. HTTPRoute: Defines HTTP-specific rules for mapping traffic from a Gateway listener to a representation of backend network endpoints. These endpoints are often represented as a Service.

## GatewayClass

Gateway를 만들기 위해서는 GatewayClass CR을 정의한다. 그러면, controller는 이를 기반으로, Gateway 인스턴스를 생성하게 된다.

```
apiVersion: gateway.networking.k8s.io/v1
kind: GatewayClass
metadata:
  name: example-class
spec:
  controllerName: example.com/gateway-controller
```

## Gateway 

GatewayClass를 참조하여, Gateway를 생성한다. 이를 통해, traffic을 처리하거나, filtering, balancing, splitting 등의 역할을 수행하게 된다.

>A Gateway describes an instance of traffic handling infrastructure. It defines a network endpoint that can be used for processing traffic, i.e. filtering, balancing, splitting, etc. for backends such as a Service.

Gateway의 예시로는, Cloud provider에서 제공하는 로드 밸런서나, in-cluster load balancer 등, http traffic을 처리하는 등을 수행한다

```
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  name: example-gateway
spec:
  gatewayClassName: example-class
  listeners:
  - name: http
    protocol: HTTP
    port: 80
```

## HttpRoutes

path, header 등, HTTP Request를 기반으로, 어떤 백엔드 서비스로 트래픽을 라우팅할지에 대한 룰을 정의한다. HttpRoutes CR을 정의하면, Gateway 인스턴스는 이를 통해, HTTP trafiic을 제어하게 된다.

```
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: example-httproute
spec:
  parentRefs:
  - name: example-gateway
  hostnames:
  - "www.example.com"
  rules:
  - matches:
    - path:
        type: PathPrefix
        value: /login
    backendRefs:
    - name: example-svc
      port: 8080
```
