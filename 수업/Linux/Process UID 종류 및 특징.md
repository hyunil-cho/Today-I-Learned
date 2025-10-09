# 리눅스의 UID와 setuid 동작 원리 정리

리눅스에서 프로세스의 권한은 **UID(User ID)** 값에 따라 결정된다.  
대표적으로 다음 네 가지 UID가 존재한다:

| 종류 | 설명 |
|------|------|
| **Real UID (RUID)** | 실제 명령을 실행한 사용자의 ID. 즉, “이 프로세스를 시작한 사람”의 UID |
| **Effective UID (EUID)** | 현재 프로세스가 **커널 수준에서 권한을 판정할 때** 사용하는 UID |
| **Saved set-user-ID (SUID)** | 프로세스가 실행될 때의 EUID 값을 저장해 둔 것. 나중에 권한을 복원할 때 사용 |
| **Filesystem UID (FSUID)** | 파일 접근 권한을 판단할 때 사용되는 UID (특정 상황에서만 별도로 존재) |

---

## setuid 비트란?

리눅스의 실행 파일에 **setuid 비트**(`s` 권한 비트)가 설정되어 있다면,  
그 프로그램을 실행하는 사용자는 **자신의 UID가 아니라 파일 소유자의 UID로 실행**하게 된다.

예를 들어 `/usr/bin/passwd` 파일은 root 소유이며 setuid 비트가 설정되어 있다.

```bash
$ ls -l /usr/bin/passwd
-rwsr-xr-x 1 root root 54256 Mar  7 12:34 /usr/bin/passwd
