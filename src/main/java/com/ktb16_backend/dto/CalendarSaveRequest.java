package com.ktb16_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "AI 결과 기반 캘린더 저장 요청 DTO")
public class CalendarSaveRequest {

    @Schema(description = "캘린더로 저장할 AI 결과 ID", example = "1")
    public Long aiResultId;
}