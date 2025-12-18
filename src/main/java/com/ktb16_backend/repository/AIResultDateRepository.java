package com.ktb16_backend.repository;

import com.ktb16_backend.domain.AIResultDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AIResultDateRepository extends JpaRepository<AIResultDate, Long> {

    // 특정 AI_RESULT의 날짜 목록 조회
    List<AIResultDate> findByAiResultId(Long aiResultId);
}