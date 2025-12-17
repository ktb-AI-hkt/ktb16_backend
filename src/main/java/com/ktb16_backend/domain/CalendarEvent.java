package com.ktb16_backend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "calendar_event")
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연관 관계
    // AI_RESULT (1) : CALENDAR_EVENT (0..N)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ai_result_id", nullable = false)
    private AIResult aiResult;

    // 기본 정보
    @Column(nullable = false)
    private String title;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    // To-Do
    @Column(name = "is_done", nullable = false)
    private boolean isDone = false;

    @Column(length = 255)
    private String memo;

    // 생성 시각
    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected CalendarEvent() {
        // JPA 기본 생성자
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 팩토리 메서드
    public static CalendarEvent create(
            AIResult aiResult,
            String title,
            LocalDate startDate,
            LocalDate endDate
    ) {
        CalendarEvent event = new CalendarEvent();
        event.aiResult = aiResult;
        event.title = title;
        event.startDate = startDate;
        event.endDate = endDate;
        event.isDone = false;
        return event;
    }

    // getter / setter
    public Long getId() {
        return id;
    }

    public AIResult getAiResult() {
        return aiResult;
    }

    public void setAiResult(AIResult aiResult) {
        this.aiResult = aiResult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}