package com.ktb16_backend.repository;

import com.ktb16_backend.domain.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long> {

    // 캘린더 조회
    // 전체 조회 (날짜순)
    List<CalendarEvent> findAllByOrderByStartDateAsc();

    // 기간 조회 (월별 / 범위)
    List<CalendarEvent> findByStartDateBetweenOrderByStartDateAsc(
            LocalDate start,
            LocalDate end
    );

    // To-Do 조회
    // 완료 / 미완료 To-Do 조회
    List<CalendarEvent> findByIsDone(boolean isDone);
}