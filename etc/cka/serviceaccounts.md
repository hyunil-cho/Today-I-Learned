# 종류

User account와, Service Account 

1. User account : 유저 정보를 인증하기 위한 데이터
2. Service Account : Application이 자신을 인증하기 위해 사용하는 데이터

# TokenRequestAPI 

Service Account를 생성하면 같이 생성되는 토큰은, JWT 기반의 토큰이지만, 기한이 없다는 문제가 있음. 이에, TOkenRequestAPI가 도입되었으며
보안성을 향상시킴.

- Audience Bound
- Time Bound
- Object Bound

  위 API를 도입한 이후, Service Account를 생성하면, token을 secret 형태로 만들어 자동으로 pod에 마운트하지 않음.
  단, 직접 토큰을 생성하여야 함(kubectl create token {sa 이름} 이렇게 생성된 토큰은 기한(expiry date)을 가지고 있음

  
  
