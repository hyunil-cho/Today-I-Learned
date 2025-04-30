# 수업 목표

웹 통신 기본 프로토콜(약속)인 HTTP에 대하여 학습하고, 이해함으로써, 외부 서비스와 연동 프로그램 작성을 위한 코드를 작성하고 이해할 수 있도록 하기 위함.

# HTTP 프로토콜

> HTTP(Hypertext Transfer Protocol)는 웹에서 데이터를 주고받는 서버-클라이언트 모델의 Protocol

## Web이란?

![image](https://github.com/user-attachments/assets/26bac47c-4abe-4ff2-87ff-ed422989b6f9)

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


## 
