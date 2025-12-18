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
            description = "날짜 타입 (SINGLE: 단일 날짜, RANGE: 기간, MULTIPLE: 여러 날짜)",
            example = "SINGLE"
    )
    public String dateType; // SINGLE / RANGE / MULTIPLE

    @Schema(
            description = """
                    날짜 목록
                    - SINGLE   : 날짜 1개
                    - RANGE    : 시작일, 종료일 (2개)
                    - MULTIPLE : 여러 날짜
                    """,
            example = "[\"2025-12-20\"]"
    )
    public List<LocalDate> dates;
}