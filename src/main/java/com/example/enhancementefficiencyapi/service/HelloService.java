package com.example.enhancementefficiencyapi.service;

import com.example.enhancementefficiencyapi.dto.HelloResponseDto;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public HelloResponseDto getHello(){
        return new HelloResponseDto("Service Layer OK", 200);
    }
}
