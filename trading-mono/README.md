# trading-mono

모놀리식식으로 주식 조회 사이트 개발
API GATEWAY + MSA로 먼저 할려고 하니 머리가 아파서
나중에 API GATEWAY + MSA로 떼어낼수 있게 코드 작성한다.

### 생각중
---

- currency 관련
- - enum도 좋지만, DB에서 코드 관리좀 해야 겠음
- - enum면 소스에서는 좋지만, 상세 정보 enum을 DB처럼 관리해보니 그것도 지옥이었음


### TODOS
---
- [x] USER 생성
- [x] JWT 인증
- [x] 주가 조회 API
- [ ] ACCOUNT 추가
- - ACCOUNT 설계는 DBS 참고해서 작성해보자.
- - https://www.dbs.com/dbsdevelopers/discover/index.html



# 작업 진행중 UI 
![img.png](img/img.png)

# 관련 인프라 실행
```
docker compose -f ./docker/yfinanace-server/docker-compose.yml up -d 
docker compose -f ./docker-compose.yml up -d 
```