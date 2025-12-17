package com.ktb16_backend.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ai_result_date")
public class AIResultDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 식별 관계: AI_RESULT에 종속
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ai_result_id", nullable = false)
    private AIResult aiResult;

    @Column(nullable = false)
    private LocalDate date;

    // JPA 기본 생성자
    protected AIResultDate() {
    }

    // 실제 사용하는 생성자
    public AIResultDate(AIResult aiResult, LocalDate date) {
        this.aiResult = aiResult;
        this.date = date;
    }

    // getter
    public Long getId() {
        return id;
    }

    public AIResult getAiResult() {
        return aiResult;
    }

    public LocalDate getDate() {
        return date;
    }
}