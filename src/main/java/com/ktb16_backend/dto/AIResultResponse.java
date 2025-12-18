package com.ktb16_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "AI 분석 결과 응답 DTO")
public class AIResultResponse {

    @Schema(description = "AI 결과 ID", example = "1")
    public Long id;

    @Schema(description = "일정 제목", example = "정형외과 진료")
    public String title;

    @Schema(description = "AI 요약 결과", example = "무릎 통증 관련 병원 방문 일정")
    public String summary;

    @Schema(description = "일정 카테고리", example = "병원")
    public String category;

    @Schema(
            description = "날짜 타입 (SINGLE / RANGE / MULTIPLE)",
            example = "SINGLE"
    )
    public String dateType;

    @Schema(
            description = "시작 날짜 (SINGLE / RANGE 타입에서 사용)",
            example = "2025-12-20"
    )
    public LocalDate startDate;

    @Schema(
            description = "종료 날짜 (RANGE 타입에서 사용)",
            example = "2025-12-25"
    )
    public LocalDate endDate;

    @Schema(
            description = "여러 날짜 목록 (MULTIPLE 타입에서 사용)",
            example = "[\"2025-12-20\", \"2025-12-22\", \"2025-12-24\"]"
    )
    public List<LocalDate> dates;
}