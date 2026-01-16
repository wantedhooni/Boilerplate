# 증권사 + 은행 서버를 만들어보았다.
## trading-mono



### 생각중
---

- 기본 골격 잡고, chatgpt이용해서 업무적으로 고민해보자!

- currency 관련
- - enum도 좋지만, DB에서 코드 관리좀 해야 겠음
- - enum면 소스에서는 좋지만, 상세 정보 enum을 DB처럼 관리해보니 그것도 지옥이었음


- 모놀리식식으로 주식 조회 사이트 개발
- API GATEWAY + MSA로 먼저 할려고 하니 머리가 아파서
- 나중에 API GATEWAY + MSA로 떼어낼수 있게 코드 작성한다.

### TODOS
---
- [x] USER 생성
- [x] JWT 인증
- [x] 주가 조회 API
- ACCOUNT 설계는 DBS 참고해서 작성해보자.
- https://www.dbs.com/dbsdevelopers/discover/index.html
- - [x] ACCOUNT 도메인 추가
- - [x] ACCOUNT 입/출금 내역 도메인 추가
- - [x] ACCOUNT 생성 Service / RestAPI
- - [x] ACCOUNT 조회 Service / RestAPI
- - [x] ACCOUNT 입금 Service / RestAPI
- - [x] ACCOUNT 출금 Service / RestAPI
- - [x] ACCOUNT 이체 Service / RestAPI

환전을 또 만들까?
손이 많이 가는데.. ㅠ_ㅠ



### 잡담
---

## Projections.constructor
개인적으로는 실수하기 수위서 사용하고 싶지 않은데
record를 사용하면 결국은 생성자 기반이라서...
Projection 추가될때 순서때문에 분명히 실수 할 소지가 많을건데...
그렇다고 field사용하면 너무 너무 느리고,
class / setter 사용하면 경기를 일으키고
트랜드가 참 머같다.



# 작업 진행중 UI 
![img.png](img/img.png)

# 관련 인프라 실행
```
docker compose -f ./docker/yfinanace-server/docker-compose.yml up -d 
docker compose -f ./docker-compose.yml up -d 
```