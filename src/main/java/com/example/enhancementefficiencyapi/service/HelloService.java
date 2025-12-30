package com.example.enhancementefficiencyapi.service;

import com.example.enhancementefficiencyapi.entity.VisitRecord;
import com.example.enhancementefficiencyapi.repository.VisitRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // Repository 주입을 위해 꼭 필요!
public class HelloService {

    private final VisitRecordRepository repository;

    @Transactional
    public void saveMessage(String message) {
        // 엔티티 객체를 생성해서 repository를 통해 DB에 저장합니다.
        VisitRecord record = new VisitRecord(message);
        repository.save(record);
    }
}