# Movie OpenAPI Project
# Open API 연동 및 간단한 영화 정보 페이지 만들기
* [개인 공부용 프로젝트 GitHub 주소](https://github.com/whitetigerv/HJStudy)
* [MD 파일 가이드](https://gist.github.com/ihoneymon/652be052a0727ad59601)

---

# 0. 서론

잠깐 말했지만 사용해보지 않은 환경들이 있어서 조금 어려울 수도 있어.
그래도 그동안 잘 했으니까 이번에도 잘 할 거라고 믿고 있어. 
어려운 거 있으면 고민하지 말고 언제든지 편하게 물어보고 최대한 자세하게 잘 알려줄게.

그리고 이번 과제 얘기 했을 때도 해준다고 해서 고마워. 알려주면 꼼꼼하게 다 확인해보고 부족한 점이나 수정할 곳 등 다양하게 피드백 해줄게.
항상 자신감 있게 화이팅!

---

# 1. 환경

- SpringBoot 3.4.5
- JDK 17
- Embedded Tomcat
- H2 DB (In-Memory)
- JSP (TBD)
- JavaScript (TBD)
- Jquery (TBD)
- JPA (or Hibernate)
- Logback
- API 사용:  https://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do
- Notion: https://www.notion.so/Open-API-1e12cae740e580c2b530f4b7dbd3f6f3

---

# 2. 과제 조건

TDB 는 진행하지 말 것!

1. API 호출 & 데이터 조회
    - RestTemplate 또는 WebClient을 사용하여 OpenAPI 호출
    - 획득한 데이터 파싱 및 가공
2. 쿼리 작성 및 DB 구축
    - 필요한 테이블 정의 및 DDL 작성
    - 획득한 데이터를 H2 DB에 건설
3. RestAPI 개발
    - 일별 박스오피스 조회 API
    - 주간/주말 박스 오피스 조회 API
    - 영화 상세정보 조회 API
    - 영화사 정보, 영화사 상세정보 조회 API
    - 영화인 정보, 영화인 상세정보 조회 API
4. 테스트 코드 작성 (TDB)
    - JUnit5 를 이용한 단위 테스트 및 API 호출 결과 검증
5. 화면 개발 (TDB)

   RestAPI 개발 진행 일정보고 진행 여부 생각 ⇒ 각 RestfulAPI 에 대한 화면

    - 리스트 조회 (일별, 주간/주말 박스오피스)
    - 단일 영화 정보 조회 (영화 상세정보)
    - 영화 상세정보에서 영화사, 영화인 정보 클릭시 해당 영화사, 영화인 상세정보 보여주기

---

# 3. 필요한 정보 (내가 만들기)

- API KEY: 86a5ec6ca4fbf722566f9f331b25db7e
- Logback 설정하기: logback-spring.xml
- 공통 응답

  com.movie.movie.util.advice.*

  com.movie.movie.util.exception.*

  com.movie.movie.util.response.*

- 공통 RestTemplate
- 쿼리 파일
- Back 단 Sample 파일
- Test 가능한 JUnit5 등등..
- JSP( TDB)
- H2 DB 접속 정보

  서버 실행하고 [http://localhost:81/h2](http://localhost:81/h2-console) 접속

  JDBC URL 입력: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE

---

# 4. RestAPI 개발

- 일별 박스오피스 조회 API

  오늘 날짜로 조회되는 데이터를 검색해서 없으면 API 호출을 이용해 데이터를 가져온다음 INSERT.

  오늘 날짜로 조회되는 데이터가 있으면 DB SELECT 를 해서 데이터를 가져온다.

- 주간/주말 박스 오피스 조회 API

  일별 박스오피스 조회 방식과 동일

  오늘 날짜로 조회되는 데이터를 검색해서 없으면 API 호출을 이용해 데이터를 가져온다음 INSERT

  오늘 날짜로 조회되는 데이터가 있으면 DB SELECT 를 해서 데이터를 가져온다.

- 영화 상세정보 조회 API

  리스트에서 영화 Code 를 가져와서 조회.

  여기도 동일하게 해당 영화 Code 데이터를 조회해서 없으면 API 호출을 이용해 데이터를 가져온다음 INSERT

  조회 되는 데이터가 있으면 DB SELECT 를 해서 데이터를 가져온다.

- 영화사 정보, 영화사 상세정보 조회 API

  영화 상세정보 조회 방식과 동일

  영화사 Code 데이터를 조회해서 없으면 API 호출을 이용해 데이터를 가져온다음 INSERT

  조회 되는 데이터가 있으면 DB SELECT 를 해서 데이터를 가져온다.

- 영화인 정보, 영화인 상세정보 조회 API

  영화 상세정보 조회 방식과 동일

  영화인 Code 데이터를 조회해서 없으면 API 호출을 이용해 데이터를 가져온다음 INSERT

  조회 되는 데이터가 있으면 DB SELECT 를 해서 데이터를 가져온다.

---

# 5. 주의 사항

1. Lombok 어노테이션 사용할 것

   참고: com.movie.movie.dto.TestDto.java 등

   Getter, Setter, 생성자 등 직접 구현 금지 (단, 어노테이션을 사용해서 구현이 불가능한 경우 사용 가능)

2. Controller Layer 에서 결과 값 반환할 경우 CommonResponse 사용해서 값 반환, 에러가 발생하는 경우 CommonException 반환

   참고: com.movie.movie.controller.TestController.java

3. 외부 API 호출 시 RestTemplate 사용

   RestTemplate 간단하게 Util 로 구현하였음 (com.movie.movie.util.connection.RestTemplateUtil.RestTemplateUtil.java) 호출해서 구현.

   해당 클래스에 추가 또는 수정이 필요하면 직접해보거나 어려우면 요청 (난이도 높음)

4. 테이블 생성(DDL) 작성

   소스 구동시 /resources/db/h2/schema.sql 경로의 sql 파일을 읽어서 실행하도록 설정하였음.
   테이블 관련 DDL 은 여기에 작성하여 저장

   /resources/db/h2/data.sql 은 데이터 관련한 쿼리를 저장하는 파일. 이번 실습에서는 데이터 INSERT 는 API 통해서 진행하므로 작성할 필요 없음. (참고용 테스트 소스 떄문에 작성하였음)

5. 로그 작성 시 System.out.println() 사용 금지

   logback-spring.xml 이용해 logback 사용하능하게 설정하였음. @Slf4j 어노테이션 사용해서 logback 사용

6. JPA  사용

   참고: com.movie.movie.repository.TestRepository.java

   Repository ↔ H2 DB 관련해서는 JPA 사용. (또는 Hibernate 까지 사용 가능. 둘 중 하나 선택)

7. 기타 DateUtil, StringUtil 등 Util 성 클래스가 필요하면 util 패키지 하위에 생성
8. git branch 는 본인 이니셜 branch 사용
9. A

주의 사항은 계속 추가 작성 예정