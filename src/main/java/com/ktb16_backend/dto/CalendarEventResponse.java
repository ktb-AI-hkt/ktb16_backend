package com.ktb16_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "캘린더 일정 조회 응답 DTO")
public class CalendarEventResponse {

    @Schema(description = "캘린더 이벤트 ID", example = "10")
    public Long id;

    @Schema(description = "일정 제목", example = "정형외과 진료")
    public String title;

    @Schema(description = "시작 날짜", example = "2025-12-20")
    public LocalDate startDate;

    @Schema(description = "종료 날짜", example = "2025-12-20")
    public LocalDate endDate;
}