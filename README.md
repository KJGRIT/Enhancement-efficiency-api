# 📘 Enhancement Efficiency API  
## Day 1 – Spring Boot로 REST API 서버 시작하기

---

## 📌 Day 1 목표

- Spring Boot로 **개인 API 서버 실행**
- REST API의 기본 개념 이해
- Controller의 역할 파악
- Java 객체가 JSON으로 자동 변환되는 흐름 이해

---

## 🛠 사용 기술 스택 (Day 1)

- **Java 17**
- **Spring Boot**
- **Gradle**
- **IntelliJ IDEA**
- JSON 처리: **Jackson (Spring Boot 기본 내장)**

---

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

### 2️⃣ Controller를 통한 브라우저 요청 처리

브라우저(클라이언트)의 요청을 처리하기 위해 **Controller**를 생성했다.

```java
@RestController
public class HealthController {

    @GetMapping("/health")
    public HelloResponseDto hello() {
        return new HelloResponseDto("Hello Spring Boot!", 200);
    }
}




