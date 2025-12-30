# 📘 Enhancement Efficiency API 
## 📌 목차
1. [Spring Boot로 REST API 서버 시작하기](#start-api-server)
2. [Controller / Service 분리와 구조 이해](#controller-/-service-separation)
3. [DB 연결과 데이터 영속성 확보](#connect-db-/-data-persistence)

<br>

# 2025.12.23 Day 1 – Spring Boot로 REST API 서버 시작하기<a id="start-api-server"></a>

<br>

## 📌 Day 1 목표

- Spring Boot로 **개인 API 서버 실행**
- REST API의 기본 개념 이해
- Controller의 역할 파악
- Java 객체가 JSON으로 자동 변환되는 흐름 이해
<br><br>
## 🛠 사용 기술 스택 (Day 1)

- **Java 17**
- **Spring Boot**
- **Gradle**
- **IntelliJ IDEA**
- JSON 처리: **Jackson (Spring Boot 기본 내장)**
<br><br>

## 🧠 Day 1 학습 내용 정리

### 1️⃣ Spring Boot로 서버 실행

Spring Boot 애플리케이션을 실행함으로써  
**로컬 PC에 웹 서버(Tomcat)가 구동됨**

```java
@SpringBootApplication
public class EnhancementEfficiencyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnhancementEfficiencyApiApplication.class, args);
    }
}
```


### 2️⃣ Controller를 통한 브라우저 요청 처리

브라우저(클라이언트)의 요청을 처리하기 위해 **Controller**를 생성했다.

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloResponseDto hello() {
        return new HelloResponseDto("Hello Spring Boot!", 200);
    }
}
```
· @RestController
 → 이 클래스는 **HTTP 요청을 처리하는 컨트롤러**

· @GetMapping("/hello")
 → /hello URL로 GET 요청 시 실행


### 3️⃣ REST API와의 관계
**Day 1**에 구현한 기능은 **REST API**의 최소 단위를 충족합니다.

#### 🔄 통신 흐름
- **요청 (Request):** `GET /hello`
- **응답 (Response):**
  ```json
  {
    "message": "Hello Spring Boot!",
    "status": 200
  }
  ```
REST API 조건 충족:
    · URL 기반 자원(Resource) 접근
    · HTTP Method 사용 (GET)
    · JSON 형식 응답
    · Stateless 구조

👉 HTML 화면이 아닌 **“데이터를 제공하는 API”**


### 4️⃣ Java 객체 → JSON 자동 변환 이해
Controller에서 **Java 객체**를 그대로 반환했지만, 브라우저(클라이언트)에서는 **JSON 형태**로 응답받는 원리를 정리합니다.

#### 💻 핵심 코드
```java
// Controller 내 반환 코드
return new HelloResponseDto("Hello Spring Boot!", 200);
```
이유:
    · Spring Boot 내부의 **Jackson 라이브러리**
    · Java 객체의 getter 메서드를 기준으로 JSON 자동 생성
    
👉 개발자는 JSON 변환 로직을 직접 작성하지 않음


### 5️⃣ DTO(Data Transfer Object)의 역할

데이터 전송을 위한 전용 객체인 **DTO**의 구조와 사용 목적을 정리합니다.

#### 💻 DTO 클래스 예시
```java
public class HelloResponseDto {
    private String message;
    private int status;

    // Getter 메서드 (Lombok 사용 시 @Getter)
}
```
DTO는:
    · Controller ↔ Client 간 **데이터 전달 전용 객체**
    · 비즈니스 로직을 포함하지 않음
    · JSON 응답 구조를 명확히 정의


### 6️⃣ 발생했던 문제 및 해결 (Troubleshooting)

프로젝트 초기 설정 및 개발 과정에서 겪은 시행착오와 해결 방안을 정리합니다.

| 문제 현상 | 원인 및 해결 방법 |
| :--- | :--- |
| **Controller / Service 자동 생성 안 됨** | Spring Boot는 구조를 강제하지 않으므로, **직접 패키지와 클래스를 생성**하여 레이어드 아키텍처 구축 |
| **Whitelabel Error Page (404)** | 요청 URL에 매핑된 `@RestController`가 없거나 패키지 경로가 잘못됨을 확인 후 **매핑 경로 수정** |
| **build.gradle 실행 오류** | Java 버전(17) 미일치 및 의존성 오타(`starter-web-test` 등) 확인 후 **정상적인 라이브러리 명칭으로 수정** |

> **👉 교훈**
> 프레임워크의 자동화된 기능에 의존하기보다 **프로젝트 구조와 빌드 설정(Gradle)을 직접 이해**하는 것이 문제 해결의 핵심임을 학습했습니다.

<br>

## 📂 Day 1 기준 프로젝트 구조
```
com.example.enhancementefficiencyapi
 ├─ EnhancementEfficiencyApiApplication.java
 ├─ controller
 │   └─ HelloController.java
 └─ dto
     └─ HelloResponseDto.java
```
<br>

## ✅ Day 1 결과물
· Spring Boot 서버 정상 실행<br>
· /hello REST API 구현<br>
· JSON 응답 확인<br>
· REST API 기본 요청/응답 흐름 이해
<br><br>

## ✍️ Day 1 한 줄 정리
Spring Boot로 로컬 서버를 실행하고, Controller를 통해 REST API를 구현하여
Java 객체가 JSON으로 자동 변환되는 기본 구조를 이해했다.
<br>

<br>

<br>

---
<br>

# 2025.12.24 Day 2 — Controller / Service 분리와 구조 이해<a id="controller-/-service-separation"></a>
<br>

## 🎯 Day 2 목표

· Controller와 Service의 역할 차이 이해

· 로직 분리를 통한 코드 가독성 향상

· 실무에서 사용하는 기본 패키지 구조 학습

· REST API 구조를 계층별로 이해하기
<br><br>

## 🧠 Day 2 학습 내용 정리

### 1️⃣ 왜 Controller에 로직을 넣으면 안 될까?

처음 Spring Boot를 배우면 Controller에 모든 코드를 작성하기 쉽다.
하지만 이는 실무에서 **좋지 않은 구조**다.

#### ❌ Controller에 로직을 모두 넣을 경우 문제점

· 코드가 길어져 가독성이 떨어짐

· 기능이 늘어날수록 수정이 어려움

· 테스트 코드 작성이 힘듦

· 오류 발생 시 원인 추적이 어려움

**👉 Controller는 최대한 얇게 유지해야 한다**

### 2️⃣ 역할 분리의 핵심 개념

Spring Boot에서는 역할에 따라 파일을 분리한다.

| **계층** | **역할** |
|:---|:---|
| Controller | 요청(Request) 수신, 응답(Response) 반환|
| Service | 비즈니스 로직 처리|
| Repository | DB 접근 및 데이터 관리|
| DTO |	데이터 전달 객체|

### 3️⃣ Service 계층 추가
#### HelloService 생성 
```
@Service
public class HelloService {

    public HelloResponseDto getHello() {
        return new HelloResponseDto("Hello Spring Boot!", 200);
    }
}
```
#### Service의 역할

· 실제 동작 로직 담당

· 계산, 조건처리, 데이터 가공

· Controller와 Repository 사이의 중간 계층

### 4️⃣ Controller에서 Service 호출
```
@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public HelloResponseDto hello() {
        return new HelloResponseDto("Hello Spring Boot!", 200);
    }
}
```
#### 흐름 정리
```
브라우저 요청

 → Controller (요청/응답)
 
 → Service (로직 처리)
 
 → DTO 반환
 
 → JSON 응답
```

<br>

### 📂 Day 2 기준 프로젝트 구조
```
src/main/java/com/example/enhancementefficiencyapi
├── controller
│   └── HealthController.java
├── service
│   └── HelloService.java
├── repository
├── dto
│   └── HelloResponseDto.java
└── EnhancementEfficiencyApiApplication.java

```
<br>

### ✅ Day 2 핵심 요약 및 결과

· Controller는 요청/응답만 담당한다

· Service는 비즈니스 로직을 담당한다

· 역할 분리는 가독성과 유지보수를 위한 필수 구조다

· REST API는 데이터를 주고받는 방식이다

<br>

### 📝 Day 2 한 줄 정리

“기능이 아니라 구조를 설계하는 연습을 시작했다.”

<br><br><br>

---

<br><br><br>

# 2025.12.30 Day3 - DB 연결과 데이터 영속성 확보<a id="connect-db-/-data-persistence"></a>

<br>

## 🎯 학습 목표
· **데이터 영속성(Persistence)** 의 개념 이해 및 필요성 체득

· **JPA(ORM)** 를 활용한 객체 지향적 데이터베이스 접근 방식 습득

· **계층형 아키텍처** 를 통한 계층별 책임 분리 및 데이터 흐름 이해

· **H2 DB** 환경에서 데이터 저장 및 조회 흐름의 실무적 검증

<br><br>

## 🧠Day3 학습 내용 정리
### 1️⃣ 데이터 영속성(Persistence)과 JPA
· **영속성** : 서버 재시작이나 장애 시에도 데이터가 물리적 저장소에 안전하게 보관되는 성질을 의미합니다.

· **JPA 선택 이유** : 기존 SQL 중심적 개발에서 발생하는 '패러다임 불일치'를 해결하고, **객체 중심 설계**를 통해 생산성과 유지보수성을 극대화하기 위해 도입했습니다.

<br>

### 2️⃣ 계층별 역할 분담

1. **Entity** : @Entity를 사용하여 객체 지향적인 데이터 모델을 설계하고, @GenoratedValue로 식별자 생성 전략을 DB에 위임했습니다.

2. **Service** : @Transactional을 사용하여 데이터 저장 과정의 원자성을 보장하고, **Entity - DTO 변환**을 통해 내부 구현을 캡슐화했습니다.

3. **Repository** : JpaRepository 인터페이스만으로 SQL 없이 CRUD를 구현하는 생산성을 체감했습니다.

<br>

### 3️⃣ 실습을 통한 흐름 검증

단순 구현에 그치지 않고, 아래와 같은 단계별 테스트를 통해 데이터가 실제로 어떻게 처리되는지 검증했습니다.

<br>

1. **데이터 요청** : 브라우저에서 **GET/save?msg=Sucess** 호출 

<img width="545" height="178" alt="스크린샷 2025-12-30 164047" src="https://github.com/user-attachments/assets/b8bee047-adc1-466e-8a04-2c78ddbeefb8" /><img width="500" height="500" alt="코드스크린샷1" src="https://github.com/user-attachments/assets/d3997f31-9a94-4f51-8719-68d81d726577" />

2. **로그 확인 및 DB 검증** : H2-Console에 접속하여 SELECT 쿼리 실행 후, 실행 데이터 행(Row)이 추가된 것을 육안으로 확인

<img width="500" height="500" alt="스크린샷 2025-12-30 164207" src="https://github.com/user-attachments/assets/a32fb122-0843-49c1-8c50-d5d86deb8215" />

<br>

### 📂 Day3 프로젝트 구조
```
src/main/java/com/example/enhancementefficiencyapi
├── controller
│   └── HelloController.java      # 클라이언트 요청(HTTP) 수신 및 응답 반환
├── service
│   └── HelloService.java          # 비즈니스 로직 처리 및 트랜잭션(@Transactional) 관리
├── repository
│   └── VisitRecordRepository.java  # JpaRepository 상속을 통한 DB 접근 로직 처리
├── entity
│   └── VisitRecord.java           # DB 테이블과 1:1 매핑되는 도메인 모델
├── dto
│   └── HelloResponseDto.java      # 데이터 전송 객체 (Entity 보호 및 스펙 분리)
└── EnhancementEfficiencyApiApplication.java
```

<br>

### ✅ Day3 핵심 요약 및 결과

- JPA 기반의 데이터 영속화: 메모리에만 머물던 데이터를 H2 데이터베이스에 물리적으로 저장하는 전체 프로세스를 구축했습니다.

- 비즈니스 로직의 계층화: Controller → Service → Repository로 이어지는 계층형 아키텍처를 직접 구현하며 각 계층의 단방향 데이터 흐름을 이해했습니다.

- ORM 매핑: Java 객체(@Entity)가 실제 DB 테이블로 자동 변환되고, SQL 작성 없이도 데이터가 저장되는 ORM의 핵심 가치를 체득했습니다.

- 데이터 저장 API: GET /save?msg={내용}을 통해 실시간 데이터 저장 가능

- 데이터 목록 API: GET /list를 통해 저장된 전체 데이터의 JSON 응답 확인

<br>

### 📝 Day3 한 줄 정리

"이제 데이터를 다룰 수 있는 **진짜 서버**의 기틀을 마련했습니다."

<br><br><br>

---

<br><br><br>

