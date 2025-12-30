package com.example.enhancementefficiencyapi.controller;

import com.example.enhancementefficiencyapi.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    // 브라우저에서 /save?msg=내용 이라고 입력하면 이 메서드가 실행됩니다.
    @GetMapping("/save")
    public String save(@RequestParam String msg) {
        helloService.saveMessage(msg);
        return "DB 저장 성공: " + msg;
    }
}