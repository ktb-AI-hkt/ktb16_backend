package com.ktb16_backend.controller;

import com.ktb16_backend.domain.CalendarEvent;
import com.ktb16_backend.dto.ApiResponse;
import com.ktb16_backend.exception.NotFoundException;
import com.ktb16_backend.repository.CalendarEventRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo", description = "할 일(Todo) 상태 관리 API")
public class TodoCommandController {

    private final CalendarEventRepository calendarEventRepository;

    public TodoCommandController(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    @Operation(
            summary = "Todo 완료 상태 토글",
            description = "Todo의 완료 상태(done)를 true/false로 변경합니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "상태 변경 성공"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404", description = "Todo를 찾을 수 없음"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500", description = "서버 내부 오류"
            )
    })
    @PatchMapping("/{id}/toggle")
    public ApiResponse<Boolean> toggle(
            @Parameter(description = "Todo ID", example = "1")
            @PathVariable Long id
    ) {

        CalendarEvent event = calendarEventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));

        event.setDone(!event.isDone());

        return ApiResponse.ok(event.isDone());
    }

    @Operation(
            summary = "Todo 메모 수정",
            description = "Todo에 메모를 추가하거나 수정합니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "메모 수정 성공"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404", description = "Todo를 찾을 수 없음"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "요청 바디 오류"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500", description = "서버 내부 오류"
            )
    })
    @PatchMapping("/{id}/memo")
    public ApiResponse<Void> updateMemo(
            @Parameter(description = "Todo ID", example = "1")
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        CalendarEvent event = calendarEventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));

        event.setMemo(body.get("memo"));

        return ApiResponse.ok(null);
    }
}