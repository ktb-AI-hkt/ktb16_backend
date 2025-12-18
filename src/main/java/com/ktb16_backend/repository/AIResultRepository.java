package com.ktb16_backend.repository;

import com.ktb16_backend.domain.AIResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AIResultRepository extends JpaRepository<AIResult, Long> {

    // 변환 기록 목록 조회 (최신순)
    List<AIResult> findAllByOrderByCreatedAtDesc();
}