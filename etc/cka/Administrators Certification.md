# 인증 / 인가

<img width="994" height="567" alt="image" src="https://github.com/user-attachments/assets/6b92d43c-9396-4883-8aa7-ba9ecbbbe993" />

</br>
</br>

kubectl 명령어를 수행하면, 해당 명령은, authentication(kubelet config 인증정보)를 통과해 먼저 인증된 사용자인지 확인한 이후
Authorization을 통해, 해당 요청을 수행한 사용자가, 해당 명령을 수행할 자격이 있는지 확인하는 과정을 거친다.

이를 RBAC이라고 하며, 아래와 같이 Role을 생성하여 다양한 명령 및 오브젝트를 정의할 수 있다. 이때, 동일한 오브젝트라도
이름이나, 네임스페이스까지 지정하여 상세하게 Auth를 수행할 수 있다.

</br>

<img width="1017" height="362" alt="image" src="https://github.com/user-attachments/assets/f5222529-9cac-44e3-89d8-bf45f9f86eba" />

그러나 이러한 방식은 더욱 세세한 조합, 예를 들어, 파드 생성 요청 시, 파드의 이미지가 public hub로부터 받아오는 것을 막고 싶거나,
태그명으로 latest를 사용하지 못하도록 막는 등을 하고자 하는 경우, 혹은 요청을 수정하는 등을 수행하기 위해서는 위 기능 만으로는 부족하다. 

</br>
</br>

위와 같은 제약사항을 극복하기 위하여 Adminsion Controllers를 직접 구현하여 사용할 수 있다. 

</br>
</br>


<img width="1024" height="557" alt="image" src="https://github.com/user-attachments/assets/9dc92cd6-8dcd-4e0c-8c29-5af370754e1d" />
<img width="1017" height="384" alt="image" src="https://github.com/user-attachments/assets/144ff22f-a9ed-46cd-8696-70988b5c6ec7" />

</br>
</br>

물론, 쿠버네티스에 이미 빌트인으로 포함된 여러 admission controller도 여럿 존재한다.

</br>
</br>
<img width="185" height="303" alt="image" src="https://github.com/user-attachments/assets/89022ea6-2062-4602-b63f-22af08abade5" />

</br>

아래와 같이 api 서버 실행 시, 사용된 admission controller 명령어를 확인할 수 있다.

<img width="1032" height="225" alt="image" src="https://github.com/user-attachments/assets/6ca27482-3f60-4eb2-b639-0daf7c8b9e61" />

