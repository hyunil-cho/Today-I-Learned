# requests library
>Requests allows you to send HTTP/1.1 requests extremely easily. There’s no need to manually add query strings to your URLs, or to form-encode your PUT & POST data — but nowadays, just use the json method!

## requests가 제공하는 기능

1. GET, POST, PUT, DELETE, HEAD, OPTIONS 요청 지원
2. URL 파라미터 자동 인코딩 - URL에는 쓸 수 있는 문자가 한정돼 있어, 필요한 경우 URL 인코딩을 수행해야 하는데, 이를 자동으로 처리함
3. JSON 응답 자동 파싱 (response.json())
4. 쿠키, 세션 관리
5. 타임아웃 설정, 리다이렉트 제어 - 클라이언트가 서버의 응답을 기다리는 최대의 시간을 설정(timeout)
6. 파일 업로드 및 다운로드

#### #Cookie & Session (참고)

> HTTP 프로토콜은 기본적으로 상태를 가지고 있지 않는다. 하지만, 웹 어플리케이션이 점차 발전하면서, 개인화된 서비스의 수요가 증가하였고, 이를 처리하기 위하여 서버가 클라이언트 별로 상태를 기억해야 할 필요가 증가하게 되었다. 이를 처리하기 위해, 서버는 클라이언트에게 cookie를 주고, 클라이언트는 받은 cookie를 서버에게 요청할 때마다 보냄으로써, 무상태 프로토콜에 상태라는 개념을 가능케 한다.

### GET, POST, PUT, DELETE, HEAD, OPTIONS 요청 지원

#### GET & POST

requests를 통해, get,post,delete,head 등, 표준 HTTP 메서드를 간편하게 사용할 수 있으며, 필요에 따라서는
request body에 데이터를 추가할 수 있다. 

```
import requests
response = requests.get("https://api.github.com/users/octocat")
```

```
import requests
payload = {'username': 'test', 'password': 'secret'}
# api에 따라서는, HTTP Request body에 JSON 타입의 데이터가 필요할 수 있다.
response = requests.post(URL, data=payload)
```

또한, get(), post() 등 메서드 호출 시, Response 타입의 객체를 반환하는데, 이 객체 내부에는
API 호출에 대한 HTTP Response 데이터가 담겨있으며, 필요에 따라 적절한 값을 꺼내어 사용할 수 있다.

일례로, response body는 물론, header, 응답코드 등을  확인할 수 있으며, 이를 통해, 적절한 조치를 취할 수 있다.

### URL 파라미터 자동 인코딩

