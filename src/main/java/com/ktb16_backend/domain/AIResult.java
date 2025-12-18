package com.ktb16_backend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ai_result")
public class AIResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // AI가 추출한 일정 제목
    @Column(nullable = false)
    private String title;

    // AI 요약 결과
    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;

    // 날짜 타입 (SINGLE / RANGE / MULTIPLE / NONE)
    @Column(name = "date_type", nullable = false, length = 20)
    private String dateType;

    // 일정 시작일 (NONE 타입일 경우 null)
    @Column(name = "start_date", nullable = true)
    private LocalDate startDate;

    // 일정 종료일 (NONE 타입일 경우 null)
    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    // 생성 시각
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // AI_RESULT (1) : AI_RESULT_DATE (0..N)
    @OneToMany(
            mappedBy = "aiResult",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AIResultDate> dates = new ArrayList<>();

    // 생성 시각 자동 세팅
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 연관관계 편의 메서드
    public void addDate(LocalDate date) {
        this.dates.add(new AIResultDate(this, date));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getDateType() {
        return dateType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<AIResultDate> getDates() {
        return dates;
    }
}