# 📘 Enhancement Efficiency API 
## 📌 목차
1. [Spring Boot로 REST API 서버 시작하기](#start-api-server)
2. [Controller / Service 분리와 구조 이해](#controller-/-service-separation)

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
