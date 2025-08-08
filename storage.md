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

만약, 이렇게 변경된 컨테이너가 삭제된다면, writable layer 상에 생성된 변경점은 모두 삭제된다.

<img width="1770" height="1015" alt="image" src="https://github.com/user-attachments/assets/78de3ecf-c6e3-4cc3-bd40-5d9f99bbd78f" />

</br>

<img width="1300" height="682" alt="image" src="https://github.com/user-attachments/assets/bafeaa9c-bcb0-4be6-967d-58a5c22c5377" />

</br>
</br>

## What if we want to persist data ?

볼률을 생성하여, 컨테이너 내에서, 변경된 파일을 영속화할 수 있다. 이를 위해, 볼륨을 생성하고, 이를 컨테이너 내에 마운트하면 된다.
이때, 두 가지 방식으로 불륨 마운팅이 가능한데, 첫 번째는 Volumne Mount이고, 다른 하나는, blind mount이다. 볼륨 마운트는, /var/lib/docker/volumes 등 이미 정해진
도커 볼륨 데이터 저장소에 볼륨을 생성하고, 이를 컨테이너에 마운트하는 것이고, 다른 하나는, 도커 호스트 상의 임의의 장소를 마운트하는 것이다.

</br>

<img width="1667" height="992" alt="image" src="https://github.com/user-attachments/assets/0a9a30b7-4348-43c7-9898-e6a7f31410c3" />

