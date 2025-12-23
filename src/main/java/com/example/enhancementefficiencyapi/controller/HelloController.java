package com.example.enhancementefficiencyapi.controller;

import com.example.enhancementefficiencyapi.dto.HelloResponseDto;
import com.example.enhancementefficiencyapi.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
