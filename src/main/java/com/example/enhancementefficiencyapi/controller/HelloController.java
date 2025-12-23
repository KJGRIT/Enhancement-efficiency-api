package com.example.enhancementefficiencyapi.controller;

import com.example.enhancementefficiencyapi.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloResponseDto hello() {
        return new HelloResponseDto("Hello Spring Boot!", 200);
    }
}
