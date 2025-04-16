# What is Persistence context?
* Persistence Context란, JPA EntityManager 내부에서 생성되는 일종의 논리적 영역으로, 트랜잭션의 시작과 함께 생성되어, rollback, commit 등 트랜잭션이 닫힐 때 함께 사라지는 영역이다.
* JPA는, 이 영역을 통해, 지연로딩, Repeatable Read, 변경 감지 등을 수행한다.
* JPA는 영속 상태의 엔티티에 대해 변경 사항을 추적하며, 트랜잭션 커밋 시점에 이를 기반으로 필요한 SQL을 동적으로 생성하고 실행한다.

# Entity의 상태
### Entity는 Persistence context 내부에서 크게 다음과 같은 상태를 가진다.
1. 비영속(new/transient): 아직 persistence context와 관련이 없는 상태
2. 영속(managed) : context에 의하여 관리되는 상태
3. 준영속(detached) : context에 관리되다가 분리된 상태
4. 삭제(removed) : context에서 삭제된 상태

### 상태 별 동작
1. 비영속 상태의 Entity에 변경이 일어나더라도, JPA는 이를 감지하지 못하기 때문에 update sql이 실행되지 않는다.
2. 영속 상태의 Entity에 변경이 일어날 경우, update SQL이 실행된다. 이때, 기본적으로 JPA는 모든 필드를 모두 업데이트한다(구현체 별로 Dynamic하게 할 수 있다.)
3. 준영속 상태의 Entity에 변경이 일어나더라도, Update SQL은 실행되지 않는다.
4. entity가 삭제되는 경우, delete 문이 실행되며, 해당 entity는 persistence context에서 삭제된다.
