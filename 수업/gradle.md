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


# Gradle Wrapper

> The Wrapper is a script that invokes a declared version of Gradle and is the recommended way to execute a Gradle build. It is found in the project root directory as a gradlew or gradlew.bat file:

![image](https://github.com/user-attachments/assets/d68fefca-104a-4327-add8-0f80bed78a85)


![image](https://github.com/user-attachments/assets/5c3cd893-b603-4455-b9bf-b9872e68b735)

## Gradle Wrapper Benefits

1. 프로젝트 표준화
2. 협업을 위한 공통 환경 제공

-> Gradle wrapper를 통해 빌드 태스크를 수행하도록 하여, 빌드 과정의 공통화 및 표준회를 위해 권장됨


![image](https://github.com/user-attachments/assets/11d0c2c3-dade-4a6c-b280-4e586608cf95)

![image](https://github.com/user-attachments/assets/585e716e-4581-4501-bf19-dc4791d14376)


# Command-Line Interface Basics

그레이들은 CLI를 통해 IDE 밖에서도 사용이 가능함


![image](https://github.com/user-attachments/assets/616cf2e5-b4de-4fdc-b13d-29e5a0f3c4e9)


# Settings File Basics - settings.gradle

1. 그레이들 프로젝트의 엔트리포인트로, 세팅파일의 목적은 서브프로젝트를 빌드에 추가하기 위함이다.
2. 그레이들은 싱글 프로젝트, 멀티 프로젝트 모두를 지원하며, 싱글 프로젝트의 경우, settings.gradle은 선택사항이지만, 멀티프로젝트라면 필수!
3. groovy, 혹은 ㅗ틀린으로 작성이 가능한 스크립트 파일로, 코틀린으로 작성된다면 확장자 명이 kts로 끝난다.(ettings.gradle.kts)
4. settings.gradle 파일은, root project 상에서 보통 사용된다.

![image](https://github.com/user-attachments/assets/77e979c2-1144-4b59-be84-268af83446ae)

# Build File Basics

모든 그레이들 프로젝트는, 최소 하나의 빌드 스크립트로 구성되어 있으며, 코틀린(확장자 kts) 혹은 groovy로 작성가능하고, 아래와 같은 내용을 담고 있음

1. 빌드 스크립트가 의존하는 플러그인, 라이브러리, 태스크
2. 소스코드가 의존하는 라이브러리

![image](https://github.com/user-attachments/assets/124acacc-0a94-41b5-9b75-86411b8d63a7)

## What is Plugins?

> Plugins extend Gradle’s functionality and can contribute tasks to a project.
> > Adding a plugin to a build is called applying a plugin and makes additional functionality available.
https://docs.gradle.org/current/userguide/build_file_basics.html
