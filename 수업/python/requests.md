# requests library
>Requests allows you to send HTTP/1.1 requests extremely easily. There’s no need to manually add query strings to your URLs, or to form-encode your PUT & POST data — but nowadays, just use the json method!

## requests가 제공하는 기능

1. GET, POST, PUT, DELETE, HEAD, OPTIONS 요청 지원
2. URL 파라미터 자동 인코딩
3. JSON 응답 자동 파싱 (response.json())
4. 쿠키, 세션 관리
5. 타임아웃 설정, 리다이렉트 제어
6. 파일 업로드 및 다운로드

### GET, POST, PUT, DELETE, HEAD, OPTIONS 요청 지원

#### GET & POST

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


