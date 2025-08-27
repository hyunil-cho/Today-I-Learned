# APllication failure

순서도

1. 웹 어플리케이션이라면 접근이 가능한지?
2. 서비스로 연결하는 구조라면, 서비스가 파드와 적절히 매칭 되었는지? -> 서비스의 상태를 체크하고, endpoint가 연결된 곳이 있는지 확인
3. 파드의 상태 확인 (restarts는 없는지? status는 어떤지 등) -> kubectl describe pod {podname}을 통해, 파드의 상태 및 이벤트를 확인 가능. 혹은 kuybectl logs {podname}을 통해 로그 확인
4. kubectl logs {podname} -f(계속 체크), --previous(이전 파드의 로그 확인 가능)
