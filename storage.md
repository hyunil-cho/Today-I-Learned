# Storage with containers

## Storage Drivers / Volume Drivers

## Storage Drivers / File System

컨테이너 이미지(예를 들어 도커 이미지)는 Layer 기반으로 구성되어 있다. 예를 들어, 도커 파일을 이용해 도커 이미지를 만든다면,
각 도커 파일 내의 명령어를 통해 각각의 대응하는 레이어를 생성하고, 이를 이전 레이어에 쌓아올려 이미지를 만들게 된다.

이러한 방식의 장점은, 동일한 레이어를 재사용하여 디스크를 효율적으로 사용할 수 있다는 점이다. 이게 가능한 이유는, 각 레이어는 
오직 읽기만 가능하며, 수정은 불가능하기 때문이다.

이후, 생성된 이미지를 run 커맨드를 이용해 실행하면, 읽기 전용 레이어 위에 writable layer가 추가되며, 이 안에서, 어플리케이션이 실행되며
변경되는 점이 반영된다. 이때, 만약 read-only layer 내에 존재하는 파일을 변경하고자 한다면, 컨테이너는, 해당 파일을 copy on write 메커니즘을 통해
복사하여, writable layer에 복사한 후, 해당 파일을 변경한다. 그렇기 때문에, read-only는 계속 지켜질 수 있게 된다.

<img width="1770" height="1015" alt="image" src="https://github.com/user-attachments/assets/78de3ecf-c6e3-4cc3-bd40-5d9f99bbd78f" />

</br>

<img width="1300" height="682" alt="image" src="https://github.com/user-attachments/assets/bafeaa9c-bcb0-4be6-967d-58a5c22c5377" />


