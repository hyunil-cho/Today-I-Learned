# Before Container

## 일반적인 개발 프로세스

```

코드 작성 및 빌드 -> 운영 서버에 최종 산출물 업로드 -> 관련된 라이브러리 업로드 -> 산출물 실행 및, 런타임 클래스 패스 지정


EX) java -cp myapp.jar:lib/* com.example.Main

```

### 이때, 하나의 서버에 하나의 JVM만 띄운다면 큰 문제가 되지 않음. 하지만, 동일한 서버에 두 개 이상의 JVM 프로세스를 실행시킨다면?


이 경우, 클래스패스를 별도로 잡아주던가, 공유 라이브러리 폴더를 함께 쓰는 방식을 생각해 볼 수 있다. 하지만, 더 큰 문제는 서로 다른 어플리케이션이 
동일하지만, 버전이 다른 라이브러리에 동시에 의존하는 경우 문제가 된다. 이때는 JVM이 클래스를 로딩하는 순서에 따라서 NoSuchMethod나 ClassNotFoundException 등이 발생할 수 있다.

[관련 오라클 문서]([https://docs.oracle.com/javase/8/docs/](https://blogs.oracle.com/javamagazine/post/how-the-jvm-locates-loads-and-runs-libraries)

이 때문에, 공유 라이브러리 폴더를 사용하지 않거나, Fat Jar(모든 의존성이 있는 라이브러리를 JAR에 함께 묶는 방식) 등을 사용하여 위와 같은 번거로움을 피할 수 있다.

### 하지만, 이로 인하여 발생하는 오버헤드(JAR 크기 증가, 라이브러리 폴더 관리, 버전 관리 등)가 발생하여, 운영 측면에서 비용이 증가하게 된다.
