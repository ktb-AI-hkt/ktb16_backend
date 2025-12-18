package com.ktb16_backend.controller;

import com.ktb16_backend.dto.CalendarEventResponse;
import com.ktb16_backend.service.CalendarQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@Tag(name = "Calendar", description = "캘린더 일정 조회 API")
public class CalendarQueryController {

    private final CalendarQueryService calendarQueryService;

    public CalendarQueryController(CalendarQueryService calendarQueryService) {
        this.calendarQueryService = calendarQueryService;
    }

    // 전체 조회
    @Operation(
            summary = "캘린더 전체 일정 조회",
            description = "저장된 모든 캘린더 일정을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping
    public List<CalendarEventResponse> findAll() {
        return calendarQueryService.findAll();
    }

    // 기간 조회 (월별/범위)
    @Operation(
            summary = "기간별 캘린더 일정 조회",
            description = "시작일과 종료일을 기준으로 캘린더 일정을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "날짜 파라미터 형식 오류"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/range")
    public List<CalendarEventResponse> findByRange(
            @Parameter(description = "조회 시작 날짜", example = "2025-12-01")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate start,

            @Parameter(description = "조회 종료 날짜", example = "2025-12-31")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate end
    ) {
        return calendarQueryService.findByRange(start, end);
    }
}