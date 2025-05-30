ㅐㅣ# requests library
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

![image](https://github.com/user-attachments/assets/b2a30b92-6285-43fd-a559-8449346c1cf1)

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


# 실습 - 동행복권 API를 통한 복권 정보 획득 1-10회까지 결과 중, 가장 많이 나온 숫자 5개 추출해보기 

get https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo={drwNo}

API 정보 
```
{
  totSellamnt: 81032551000,
  returnValue: "success",
  drwNoDate: "2019-06-01",
  firstWinamnt: 4872108844,
  drwtNo6: 25,
  drwtNo4: 21,
  firstPrzwnerCo: 4,
  drwtNo5: 22,
  bnusNo: 24,
  firstAccumamnt: 19488435376,
  drwNo: 861,
  drwtNo2: 17,
  drwtNo3: 19,
  drwtNo1: 11
}
```

# 과제 

Gmail API와 연동 후, Excel에서 고객정보를 불러와 이메일 보내기 자동화 프로그램 개발

## 개발 프로세스 

1. Excel에서 사용자 데이터 loading
2. 1에서 불러온 데이터를 처리할 수 있도록 pandas 객체로 변환
3. Gmail API를 이용하여 이메일 전송

### OAuth

> Open Authorization의 약자로, 외부 애플리케이션이 사용자의 비밀번호를 직접 알지 않고도 특정 서비스(예: Gmail, Facebook 등)에 제한된 접근 권한을 부여받을 수 있도록 해주는 인증 프로토콜
> > 로그인에 성공할 경우, refresh token과, access token을 발급받게 된다. 이때, access token을 이용해 api를 사용하며, 시간이 만기(expired) 된 경우, refresh token을 이용하여 access token을 재발급받는다. 이때, refresh token은 access token 보다 유효기간이 길며, 이 마저도 만료되면 다시 로그인을 수행해야 한다.

![image](https://github.com/user-attachments/assets/c5c4b473-354c-4f06-aff7-a45100092546)

### OAuth를 통해 얻을 수 있는 효과
- 비밀번호 노출 없이 안전한 권한 부여
- 특정 기능만 제한적으로 허용 (전체 계정 접근 X)
- 언제든지 접근 권한을 취소할 수 있음

Cf) https://ohou.se/ 로그인


