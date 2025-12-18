package com.ktb16_backend.controller;

import com.ktb16_backend.dto.ApiResponse;
import com.ktb16_backend.dto.CalendarSaveRequest;
import com.ktb16_backend.dto.CalendarSaveResponse;
import com.ktb16_backend.service.CalendarEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@Tag(name = "Calendar", description = "캘린더 일정 관리 API")
public class CalendarEventController {

    private final CalendarEventService calendarEventService;

    public CalendarEventController(CalendarEventService calendarEventService) {
        this.calendarEventService = calendarEventService;
    }

    @Operation(
            summary = "AI 결과 기반 캘린더 일정 저장",
            description = "AI 분석 결과 ID를 받아 해당 일정들을 캘린더에 저장합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "캘린더 저장 성공"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "요청 값 오류"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404", description = "AI 결과를 찾을 수 없음"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500", description = "서버 내부 오류"
            )
    })
    @PostMapping("/save")
    public ApiResponse<List<CalendarSaveResponse>> save(
            @RequestBody CalendarSaveRequest request
    ) {
        return ApiResponse.ok(
                calendarEventService.saveFromAIResult(request.aiResultId)
        );
    }
}