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

# Dependency Management Basics

- 그레이들은 의존성 관리 지원 기능이 내장되어 있음
- 의존되는 대상의 종류는, 라이브러리, 플러그인, Jars, 소스코드 등이 될 수 있음

  ![image](https://github.com/user-attachments/assets/579861d4-88bb-413f-b58e-3645b67a8077)

### (참고) Version Catalog

 멀티 프로젝트(프로젝트가 두 개 이상인 경우)의 경우, 여러 서브프로젝트 별로 동일한 라이브러리를 사용하는 경우, 버전 관리가 힘들 수 있는데,
 이러한 문제를 풀기 위해 그레이들은 libs.versions.toml을 제공하며, 여기에 버전을 명시하고, 각 서브프로젝트가 해당 파일을 참조하도록 하여, 관리를 중앙화할 수 있음

 ![image](https://github.com/user-attachments/assets/13eb7262-9db6-4e34-bdeb-ac77407e4688)

# Task Basics

Task란, 그레이들에서 최소 작업 단위로, task 각각은 독립된 단위로 실행 및 관리되며, 다음과 같은 작업을 task라고 부른다

- compile class
- generate JAR(Executable Job)
- publishing archive to a repository
- execute tests

## execute a task

gradle wrapper 혹은 gradle 명령어를 통해 태스크를 실행할 수 있으며, 다음과 같이, 실행하고자 하는 task 명을 뒤에 붙인다.
이때, 복수개의 태스크를 동시에 실행하거나, 태스크에 인자를 넣어, 프로그래밍할 수 있다.
</br>
</br>
`
$ ./gradlew build
`

이때, 태스크는 직접 정의할 수 있으며, 혹은 빌드 스크립트 내의 플러그인(java)을 통해 자동으로 등록되기도 한다.

사용가능한 태스크 목록을 조회하기 위해서는 아래와 같이 tasks 태스크를 실행하면, 실행가능한 태스크 목록을 확인할 수 있다.

</br>

`
$ ./gradlew tasks
`
## task dependency

여러 태스크 사이에서 의존성을 맺어줄 수 있다. 예를 들어, A,B라는 태스크가 있을 때, B는 반드시 A보다 다음에 실행되어야 하는 경우가 있을 수 있다. 이런 경우, A,B 사이의 의존성을 맺어주어, 그레이들로 하여금, 실행 순서를 제어할 수 있다.

![image](https://github.com/user-attachments/assets/279eefbd-8576-4e89-8452-cb1f7aa480d7)

# Plugin Basics

> A plugin is a reusable piece of software that provides additional functionality to the Gradle build system. It can:

- Add new tasks to your build (like compileJava or test)

- Add new configurations (like implementation or runtimeOnly)

- Contribute DSL elements (like application {} or publishing {})

- plugins 블록에 플러그인을 삽입할 수 있으며, 그레이들은, 명시된 플러그인을 다운로드 받거나, 이미 저장된 플러그인을 로컬에서 꺼내와 사용할 수 있다.

![image](https://github.com/user-attachments/assets/ec1823bd-dd2c-4ff9-9c06-b65c82cf8fdc)

-  java-library 플러그인을 사용할 수 있다. 이를 통해, 자바를 컴파일하거나, 테스트를 수행하는 등의 일을 할 수 있다.
-  spotless 플러그인을 사용할 수 있다. 이는, 코드 포맷팅이나 프리티어 같은 외부 툴과 연동할 수 있게 한다.

## 플러그인 타입 및 출처

### 타입
1. Script plugins – Reusable .gradle or .gradle.kts files that are applied using apply from:.
2. Pre-compiled plugins – Packaged Kotlin or Groovy code applied with the plugins {} block.
3. Binary plugins – Packaged and published plugins (often from the Plugin Portal or Maven) that are applied with the plugins {} block.

### 출처

1. gradle에 기본 내장된 플러그인
2. maven 등, 외부에서 다운(빌드 시, 그레이들이 자동으로 다운 cf@  Gradle Plugin Portal )
3. 직접 개발 
   
# Incremental Builds and Build Caching Basic

 그레이들은 빌드에 필요한 시간을 축소하기 위하여, incremental builds and build caching 매커니즘을 이용한다.

## Incremental Build

> An incremental build is a build that avoids running tasks whose inputs have not changed since the previous build

 즉, 이전 태스크의 인풋값과 현재 태스크의 인풋값이 같다면, 이전 태스크 실행 결과를 캐싱하고, 이를 재사용할 수 있다. 이를 통해, 불필요한 
태스크를 제외함으로써, 전체적인 실행 시간을 줄일 수 있다. 이를 위해, 그레이들은 빌드타임에 인풋을 검사하고, 변하지 않았다면, Incremental build를 수행한다.

## Build Caching

> Incremental Builds are a great optimization that helps avoid work already done. If a developer continuously changes a single file, there is likely no need to rebuild all the other files in the project.



![image](https://github.com/user-attachments/assets/5eefce4e-f29b-4a26-9e14-19a5d290eb43)

# 과제

### 목적 
멀티 프로젝트 개발

### 요구사항

utils 프로젝트와, A 프로젝트를 추가하는데, A프로젝트 Utils 프로젝트에 의존한다.

Utils.Commons 클래스가 존재하며, 이 안에, public static LocalDateTime getNow(String strDate);

A.Service.printNow(String now): -> Utils.Commons.getNow에 의존하는 메서드 작성

utils 프로젝트와 A 프로젝트는 각각 서브프로젝트로써 존재해야 함.
