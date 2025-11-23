# UMC 미션 관리 시스템

UMC 3주차 미션 - 가게 리뷰 및 미션 도전 API 구현

## 프로젝트 개요

가게 리뷰 작성 및 미션 도전 기능을 제공하는 REST API 서버

## 주요 기능

### 1. 가게 리뷰 API
- 리뷰 작성 (하드코딩 회원 ID: 1)
- 가게별 리뷰 조회
- 회원별 리뷰 조회

### 2. 미션 도전 API
- 미션 도전하기 (하드코딩 회원 ID: 1)
- 도전 중인 미션 목록
- 완료한 미션 목록
- 미션 완료 처리

## 기술 스택

- Java 21
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database (메모리 DB)
- QueryDSL 5.0.0
- Lombok
- SpringDoc OpenAPI 2.2.0

## 프로젝트 구조

```
src/main/java/com/umc/
├── domain/              # Entity
│   ├── Member.java
│   ├── Store.java
│   ├── Review.java
│   ├── Mission.java
│   ├── MemberMission.java
│   └── enums/MissionStatus.java
├── repository/          # JPA Repository
├── dto/                 # Request/Response DTO
├── service/             # Business Logic
├── controller/          # REST Controller
├── config/              # Configuration
└── exception/           # Exception Handler
```

## 실행 방법

### 데이터베이스 설정

H2 메모리 DB 사용 (별도 설치 불필요)
- 애플리케이션 실행 시 자동 생성
- 종료 시 데이터 삭제

### 실행

```bash
# Gradle
./gradlew bootRun

# 또는 IDE에서 UmcApplication.java 실행
```

### 접속 정보

- 서버: http://localhost:9098
- Swagger UI: http://localhost:9098/swagger-ui.html
- H2 Console: http://localhost:9098/h2-console
  - JDBC URL: jdbc:h2:mem:testdb
  - Username: sa
  - Password: (공백)

## API 엔드포인트

### 리뷰 API

**리뷰 작성**
```http
POST /api/reviews
Content-Type: application/json

{
  "storeId": 1,
  "rating": 4.5,
  "content": "맛있어요"
}
```

**가게 리뷰 조회**
```http
GET /api/reviews/stores/{storeId}
```

**내 리뷰 조회**
```http
GET /api/reviews/my
```

### 미션 API

**미션 도전**
```http
POST /api/member-missions
Content-Type: application/json

{
  "missionId": 1
}
```

**도전 중인 미션**
```http
GET /api/member-missions/challenging
```

**완료한 미션**
```http
GET /api/member-missions/completed
```

**미션 완료 처리**
```http
PATCH /api/member-missions/{memberMissionId}/complete
```

## 데이터베이스 스키마

### Member
- id (PK)
- name
- email
- phoneNumber
- createdAt, updatedAt

### Store
- id (PK)
- name
- address
- description
- rating
- createdAt, updatedAt

### Review
- id (PK)
- member_id (FK)
- store_id (FK)
- rating
- content
- createdAt, updatedAt

### Mission
- id (PK)
- store_id (FK)
- title
- description
- reward
- deadline
- createdAt, updatedAt

### MemberMission
- id (PK)
- member_id (FK)
- mission_id (FK)
- status (CHALLENGING, COMPLETE)
- createdAt, updatedAt

## 초기 데이터

애플리케이션 시작 시 자동 생성

**회원**
- ID: 1, 이름: 홍길동

**가게**
- ID: 1, 이름: 맛있는 식당
- ID: 2, 이름: 행복한 카페

**미션**
- ID: 1, 제목: 3회 방문하기 (가게 1)
- ID: 2, 제목: 5만원 이상 주문하기 (가게 1)
- ID: 3, 제목: 커피 10잔 구매하기 (가게 2)

## 주요 설정

### application.yml
- 서버 포트: 9098
- DB: H2 메모리 (jdbc:h2:mem:testdb)
- JPA ddl-auto: create
- H2 Console 활성화

### build.gradle.kts
- Java 21 toolchain
- Spring Boot 3.2.0
- QueryDSL 설정 포함

## Validation 규칙

### 리뷰 작성
- storeId: 필수
- rating: 0.0 ~ 5.0
- content: 5자 이상 1000자 이하

### 미션 도전
- missionId: 필수
- 중복 도전 불가

## 예외 처리

GlobalExceptionHandler에서 처리
- IllegalArgumentException: 404 NOT_FOUND
- IllegalStateException: 400 BAD_REQUEST
- MethodArgumentNotValidException: 400 BAD_REQUEST (Validation 실패)

## API 응답 형식

```json
{
  "isSuccess": true,
  "code": "200_1",
  "message": "요청 성공",
  "result": { ... }
}
```

## 참고사항

- 로그인 기능 없음 (회원 ID 하드코딩: 1)
- H2 메모리 DB 사용 (재시작 시 데이터 초기화)
- DataInitializer에서 테스트 데이터 자동 생성
- Swagger UI에서 API 테스트 가능
