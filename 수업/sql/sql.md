# SQL이란?
> SQL은 "Structured Query Language"의 약자로, 관계형 데이터베이스에서 데이터를 관리하고 처리하기 위한 프로그래밍 언어입니다. 쉽게 말해, 데이터베이스에 저장된 정보를 검색, 삽입, 수정, 삭제하는 데 사용되는 언어이다.

![image](https://github.com/user-attachments/assets/8acf9ead-4eed-41f0-b091-c0edaf71569f)

# 데이터 영속화

프로그래램 상에서 생성된 데이터는 메모리에 저장되며, 프로그램이 종료되거나, 머신을 종료할 경우, 모든 데이터가 사라지게 된다. 이러한 성질을 휘발성이라고 한다.

하지만 경우에 따라서는 데이터를 꾸준히 유지하기 위해서는 하드디스크에 저장할 필요가 생겨난다. 예를 들어, 이메일 발송 기록을 유지하거나, 유저 정보를 저장하는 등의 경우를 들 수 있다. 본 강의에서는 RDBMS에 SQL을 이용해 데이터를 저장하는 것을 목표로 한다.

# RDBMS란?

> RDBMS (Relational Database Management System)는 관계형 데이터베이스를 생성, 관리 및 업데이트하는 데 사용되는 소프트웨어입니다. 관계형 데이터베이스는 데이터를 테이블 형태로 저장하고 테이블 간의 관계를 정의하여 효율적인 데이터 관리를 제공합니다

![image](https://github.com/user-attachments/assets/79e6ec97-be46-4d80-a6ef-0aa448b0d655)

RDBMS는 데이터를 테이블이라는 논리적 단위로 나누어 저장한다. 또한, 테이블은 서로 다른 테이블과 관계를 맺을 수 있으며, 이를 통해 복잡한 현실세계를 데이터로 나타낼 수 있으며, 이를 데이터 모델링이라고 한다.

## 모델링 예시 - 학생 수업 관리 프로그램 개발을 위한 데이터 모델링

요구사항

1. 학생 정보를 관리해야 한다.
2. 수업 정보를 관리해야 한다.
3. 학생이 어떤 수업을 듣고 있는지 관리해야 한다.

![image](https://github.com/user-attachments/assets/714bb871-2973-435e-824c-38dd5b221ac5)

해설

1. 학생_관리: 학생 정보를 관리하는 테이블, 학생의 기본 정보를 관리하는 테이블
2. 수업_관리: 수업의 기본 정보 및 담당자 정보를 관리하는 테이블
3. 학생_수업_관리: 학생이 어떤 수업을 듣고 있으며, 언제 수업을 신청했는지 관리하는 테이블

# 테이블 기본 구성 요소

![image](https://github.com/user-attachments/assets/952a6eef-f1f5-430f-aaa7-3ed82fc63fd6)


1. 기본키(Primary Key) : 테이블 내의 튜플(행)을 유일하게 구분할 수 있는 칼럼. 이 칼럼은 중복되어서는 안된다. 그외 칼럼은 중복을 허용할 수 있으나, 제약조건을 걸어 중복을 방지할 수도 있다.
2. 칼럼(열) : 속성
3. 튜플(행) : 각각의 데이터로, 튜플은 기본키를 기준으로 식별된다.
