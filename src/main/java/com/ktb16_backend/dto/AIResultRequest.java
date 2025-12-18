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
            description = "AI 요약 결과 (what / when / how 구조)",
            example = """
            {
              "what": "정형외과 진료",
              "when": "무릎 통증",
              "how": "병원 방문"
            }
            """
    )
    public AISummary summary;

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

    @Schema(
            description = "OCR로 추출한 원본 텍스트",
            example = "2025년 12월 20일 정형외과 방문 예정..."
    )
    public String rawText;
}