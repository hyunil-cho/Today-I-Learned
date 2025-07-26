# Before Container

## 일반적인 개발 프로세스

```

코드 작성 및 빌드 -> 운영 서버에 최종 산출물 업로드 -> 관련된 라이브러리 업로드 -> 산출물 실행 및, 런타임 클래스 패스 지정


EX) java -cp myapp.jar:lib/* com.example.Main

```

### 이때, 하나의 서버에 하나의 JVM만 띄운다면 큰 문제가 되지 않음. 하지만, 동일한 서버에 두 개 이상의 JVM 프로세스를 실행시킨다면?


이 경우, 클래스패스를 별도로 잡아주던가, 공유 라이브러리 폴더를 함께 쓰는 방식을 생각해 볼 수 있다. 하지만, 더 큰 문제는 서로 다른 어플리케이션이 
동일하지만, 버전이 다른 라이브러리에 동시에 의존하는 경우 문제가 된다. 이때는 JVM이 클래스를 로딩하는 순서에 따라서 NoSuchMethod나 ClassNotFoundException 등이 발생할 수 있다.
이때, 라이브러리의 버전 정보는 중요치 않으며, 중요한 것은, 찾고자 하는 FQDN이다. FQDN과 일치하는 클래스를 발견하면 로딩하기 때문에, 클래스로더 관점에서 버전은 큰 의미가 없다.

> In general, if multiple classes with the same fully qualified name are available to the JVM, the conflict resolution strategy is simple and straightforward: the first appropriate class wins. 

> The same goes for JAR files that share the same name. The JAR files will be scanned in the order in which they appear in the classpath, not according to their names. If the first JAR file contains an entry for the required class, the class will be loaded. If not, the classpath scan will continue and reach the second JAR file.

[관련 오라클 문서]([https://docs.oracle.com/javase/8/docs/](https://blogs.oracle.com/javamagazine/post/how-the-jvm-locates-loads-and-runs-libraries)

이 때문에, 공유 라이브러리 폴더를 사용하지 않거나, Fat Jar(모든 의존성이 있는 라이브러리를 JAR에 함께 묶는 방식) 등을 사용하여 위와 같은 번거로움을 피할 수 있다.

### 하지만, 이로 인하여 발생하는 오버헤드(JAR 크기 증가, 라이브러리 폴더 관리, 버전 관리 등)가 발생하여, 운영 측면에서 비용이 증가하게 된다.

# Rise of Container 

위와 같은 기존의 개발 산출물의 배포 방식에서 벗어나, 더 간단하고 편하게 어플리케이션을 배포하기 위하여 어플리케이션을 컨테이너 이미지 형태로 배포하는 방식이 자주 사용되게 됨. 이때, 선구자가 '도커'이다.

<img width="1178" height="741" alt="image" src="https://github.com/user-attachments/assets/8ec0dd32-4726-4422-91c6-7c7b25da7c94" />

## 컨테이너가 도입된 이후 

1. 어플리케이션 개발 및 빌드 후, 관련 의존 라이브러리와 함께 컨테이너 이미지로 패키징
2. 생성된 컨테이너 이미지를 도커 허브와 같은 컨테이너 저장소에 저장(마치 깃허브)
3. 서버에서는 필요로 하는 컨테이너 이미지를 pull 받은 이후, 컨테이너 런타임 상에서 실행
4. 컨테이너 이미지는 컨테이너 런타임에 의하여 컨테이너로써 실행

## 컨테이너화된 배포 방식의 장점은?
1. 간편한 관리 및 실행 -> 어플리케이션이 필요로 하느 모든 것이 컨테이너 이미지 안에 모두 있기 때문에, 별도의 서버 세팅을 할 필요가 없음
2. 유동적으로 스케일 아웃을 간편하게 수행할 수 있음 -> 워크로드가 늘어나면 단순히 컨테이너 이미지로부터 새로운 컨테이너를 생성하여 부하를 분산할 수 있음
3. OS 환경에 영향을 덜 받는다. 하지만, 이 경우, 호환되는 커널의 종류에 따라 다르다

## 컨테이너?

>Containers are a technology that allow applications to be packaged and isolated with their entire runtime environment. This makes it easier to maintain consistent behavior and functionality while moving the contained application between environments (dev, test, production) and across public, private, hybrid cloud, and on-premise. Because they are lightweight and portable, containers provide opportunities for faster development and meeting business needs as they arise.

1. 리눅스 컨테이너는 운영체제 수준의 가상화 기술로 리눅스 커널을 공유하면서 프로세스를 격리된 환경에서 실행하는 기술.
2. 컨테이너란 프로세스 격리 기술로, 사용자가 사용할 프로그램과 환경 설정들이 컨테이너에 담겨 격리되어 실행.
3. 컨테이너에는 코드, 런타임, 시스템 도구, 시스템 라이브러리 등 서버에 설치되는 무엇이든 포함될 수 있으며, 실행 중인 환경에 관계 없이 언제나 동일하게 실행될 것을 보증.
   
<img width="1238" height="662" alt="image" src="https://github.com/user-attachments/assets/94b330df-a7a6-4b74-a57b-77efb47d816c" />

[관련 레드햇 문서]([[https://docs.oracle.com/javase/8/docs/](https://blogs.oracle.com/javamagazine/post/how-the-jvm-locates-loads-and-runs-libraries](https://www.redhat.com/en/topics/containers))

## 컨테이너는 어떻게 어플리케이션 간의 격리를 보장하는가?

매우 기술적이고, 리눅스에 대한 깊은 이해가 필요한 부분. cgroups, namespace 등의 기능을 이용해, 격리된 것 처럼 '보이게' 만드는 것이 컨테이너 기술이다.

[리눅스 컨테이너 관련 참고]([[https://80000coding.oopy.io/84206f08-8231-4478-ad46-ff340e693699))
















참고 
1. https://creboring.net/blog/how-docker-divide-image-layer/
2. https://80000coding.oopy.io/84206f08-8231-4478-ad46-ff340e693699

