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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;

    @Column(name = "date_type", nullable = false, length = 50)
    private String dateType; // SINGLE / RANGE / MULTIPLE

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 관계
    // AI_RESULT (1) : AI_RESULT_DATE (0..N) - 식별 관계
    @OneToMany(
            mappedBy = "aiResult",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AIResultDate> dates = new ArrayList<>();

    // AI_RESULT (1) : CALENDAR_EVENT (0..N) - 비식별 관계
    @OneToMany(mappedBy = "aiResult")
    private List<CalendarEvent> calendarEvents = new ArrayList<>();

    // 생명주기
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 연관관계 편의 메서드
    public void addDate(LocalDate date) {
        this.dates.add(new AIResultDate(this, date));
    }

    // setter
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

    // getter
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

    public List<CalendarEvent> getCalendarEvents() {
        return calendarEvents;
    }
}