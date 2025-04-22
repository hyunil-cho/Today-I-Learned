# What is Gradle?

> Gradle Build Tool is a fast, dependable, and adaptable open-source build automation tool with an elegant and extensible declarative build language.
1. 그레이들을 사용해 빌드 과정을 자동화
2. 공통의 개발 환경을 구축

   
![image](https://github.com/user-attachments/assets/a572d27c-7041-486a-b981-d61637d40667)

# Build?

## 좁은 의미에서의 빌드

프로젝트의 소스 코드, 설정, 리소스 등을 조합해서 실행 가능한 형태(예: 바이너리, 앱, 패키지 등)로 만드는 과정

## 넓은 의미에서의 빌드

넓은 의미에서의 빌드는 단순히 실행 가능한 형태로 소스코드를 만드는 것뿐만 아니라, 여러 단계를 포함할 수 있음.

1. 코드를 생성
2. 코드가 의존하는 3rd party 라이브러리를 관리
3. 코드 수준 검증을 위한 테스트
4. 빌드 이력 관리 자동화
5. ...
6. 산출물(JAR, WAR 등) 생성
7. ...

![image](https://github.com/user-attachments/assets/5a5e6b99-69a7-4558-ba1f-db0c3d806e15)

--- 

# Gradle이란?

소스코드를 만드는 것부터 시작하여, 의존성 관리, 등 최종 산출물을 만드는 과정 전체를 통틀어 관리 및 자동화하는 툴!

---


# Gradle 구조

![image](https://github.com/user-attachments/assets/fc20a670-e5dc-4aa2-9a01-5bcbbef35786)


# Gradle Core concepts

### Project

어플리케이션, 라이브러리와 같이, 빌드가 가능한 소프트웨어

#### Project 종류
1. Single Project : root Project 하나만 포함하고 있는 프로젝트
2. Multi Porject : 하나의 Root Project와, 하나 이상의 서브 프로젝트를 포함하고 있는 프로젝트

### Build Script

빌드 스크립트를 통해 그레이들로 하여금, 빌드를 하기 위해 취해야 할 단계를 명시(지시)한다.

### Dependency Management

프로젝트가 의존하는 외부 라이브러리를 선언하고, 자동으로 관리한다. 

### Tasks

가장 작은 단위의 일(Tasks are a basic unit of work)을 나타내며, 컴파일링, 혹은 테스트를 수행하는 등, 그레이들에서 다루는 가장 작은 단위의 작업이다.

# Gradle project structure

![image](https://github.com/user-attachments/assets/9f659d97-821d-4c91-9e62-72a780d738f1)
![image](https://github.com/user-attachments/assets/0cff6f1d-4d00-4b70-9dc1-51bf0b58d590)



