# Culcom 회원 정보 획득

## 기본 API 정보

Method : Get
URL : /users/{userId}

return : 
{
  "userId" : integer,
  "userName" : string,
  "address" : dict
}

## Sample
request => /users/1 <br>

{
  "userId" : 1,
  "userName" : "hyunil",
  "address" : {
     "country": "korea",
     "code" : 100
  }
}

# Culcom 회원 등록

## API 정보

Method : Post
URL : /users/
Body : {
  "userId" : integer,
  "userName" : string,
  "address" : dict
}

## Sample

Request : Post /users

body : {
 "userId" : 2,
 "userName" : "taerim",
 "address" : {
   "country" : "Japan",
   "code" : 200
 }
}

return 

| status code | 처리기준 |  |
|-------|-------|-------|
| 200   | 성공   |    |
| 400   | 클라이언트 메시지 에러   |    |
| 500   | 서버에러, 재시도   |    |
