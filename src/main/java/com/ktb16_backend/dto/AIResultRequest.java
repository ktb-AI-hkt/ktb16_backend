package com.ktb16_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "AI 분석 결과 저장 요청 DTO")
public class AIResultRequest {

    @Schema(
            description = "AI가 추출한 일정 제목",
            example = "정형외과 진료"
    )
    public String title;

    @Schema(
            description = "AI 요약 결과",
            example = "무릎 통증으로 병원 방문 일정"
    )
    public String summary;

    @Schema(
            description = "날짜 타입 (SINGLE / RANGE / MULTIPLE)",
            example = "RANGE"
    )
    public String dateType;

    @Schema(
            description = """
                날짜 목록
                - SINGLE   : 날짜 1개
                - MULTIPLE : 날짜 2개 이상
                - RANGE    : 선택 (startDate / endDate로 대체 가능)
                """,
            example = "[\"2025-01-15\", \"2025-01-17\"]"
    )
    public List<LocalDate> dates;

    @Schema(
            description = "기간 시작일 (RANGE 타입에서 사용)",
            example = "2025-01-15"
    )
    public LocalDate startDate;

    @Schema(
            description = "기간 종료일 (RANGE 타입에서 사용)",
            example = "2025-01-17"
    )
    public LocalDate endDate;
}