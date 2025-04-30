# 수업 목표
<br>
웹 통신 기본 프로토콜(약속)인 HTTP에 대하여 학습하고, 이해함으로써, 외부 서비스와 연동 프로그램 작성을 위한 코드를 작성하고 이해할 수 있도록 하기 위함.<br>     

# HTTP 프로토콜
> HTTP(Hypertext Transfer Protocol)는 웹에서 데이터를 주고받는 서버-클라이언트 모델의 Protocol  

## Web이란?

![image](https://github.com/user-attachments/assets/26bac47c-4abe-4ff2-87ff-ed422989b6f9)  
<br>
## 웹과 관련된 용어
---

- 클라이언트 => 사용자(브라우저, 크롬, 엣지, 익스플로어 등)
- 서버 => 컨텐츠 제공자로, 미리 정해진 규칙에 따라 클라이언트로부터 요청이 오면, 요청한 URL에 따라 프로그래밍된 대로 결과물을 반환
- URL => 클라이언트가 얻고자 하는 자원의 주소로, 클라이언트는 서버로부터 얻고자 하는 리소스의 주소를 명시한다.
- 클라이언트-서버 모델 => 클라이언트가 요청하면, 서버는 클라이언트의 요청에 대하여 응답하는 모델
---
![image](https://github.com/user-attachments/assets/775acedc-c03c-45f0-8d05-3d92eca0448e)
---

## URL

- 클라이언트는, URL을 통해, 어떤 서버에 접속하여, 어떤 리소스를 요청하고 싶은지 명시한다
- 서버는 URL을 통해 사용자의 요청을 확인하고, 미리 정의된 리소스를 반환한다.
  
![image](https://github.com/user-attachments/assets/0d22790b-68bf-45c0-bda0-8089d7b1bd5d)


## Protocol?

1. Protocol이란, 이해관계자간의 약속이자 규칙이다.
2. Protocol이 존재하기 때문에 이해관계자들은 서로가 어떤 행동을 할지 미리 예측하고 대비할 수 있다.

   ![image](https://github.com/user-attachments/assets/9229ffe1-391c-40fd-84f8-38255e2a4586)


## How HTTP Protocol work?

![image](https://github.com/user-attachments/assets/3d86bb0c-46f1-47b6-ba2f-87a8e28ade14)

## HTTP Request
</br>
사용자가 서버에게 보내는 메시지를 HTTP Request라고 하며, 다음과 같은 구조를 가지고 있다.

![image](https://github.com/user-attachments/assets/7c885773-3494-41da-ace8-1c1b2e061500)
## HTTP Response
서버가 사용자의 요청에 대하여 응답 메시지로, 아래와 같은 구조를 가지고 있다.
![image](https://github.com/user-attachments/assets/cfeaffc2-b5ed-4feb-8c69-b85b4430a0ec)


## OPEN API?

- Open API란, 누구나 사용할 수 있도록 **공개된 API (Application Programming Interface)**를 말합니다. 쉽게 말해, 다른 프로그램이나 서비스가 내 프로그램 기능을 이용할 수 있게 만든 창구를 공개한 것
- 여러 서비스(Gmail, Naver Samrt store, 등등)에서는 본 서비스와 연동하여 프로그램을 개발할 수 있도록 웹 서비스와 이를 사용하기 위한 메뉴얼을 제공

https://developers.google.com/workspace/gmail/api/reference/rest?hl=ko

### JSON

1. 보통 많은 웹서비스는, HTTP API 통신 시, Body에 JSON 포맷을 이용해 데이터를 구조화하고, 통신하는데 사용함
2. JSON 포맷이란, 아래와 같이 데이터를 구조화한 것을 의미
3. Python에서는 딕셔너리 자료구조를 이용하여 간편하게 json 포맷을 만들 수 있음
   
![image](https://github.com/user-attachments/assets/d0487612-4429-4658-9d56-5328529ba6bf)


# 실습
유저정보를 호출하고, 이 중, 이름이 L로 시작하는 사람만 필터링하여 출력하기
https://jsonplaceholder.typicode.com/

