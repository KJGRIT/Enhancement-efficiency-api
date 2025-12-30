package com.example.enhancementefficiencyapi.repository;

import com.example.enhancementefficiencyapi.entity.VisitRecord; // 곧 만들 Entity
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {
    // JpaRepository를 상속받는 것만으로 save(), findAll() 등을 사용할 수 있게 됩니다.
}