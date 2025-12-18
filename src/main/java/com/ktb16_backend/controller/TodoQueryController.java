package com.ktb16_backend.controller;

import com.ktb16_backend.domain.CalendarEvent;
import com.ktb16_backend.dto.ApiResponse;
import com.ktb16_backend.repository.CalendarEventRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo", description = "할 일(Todo) 조회 API")
public class TodoQueryController {

    private final CalendarEventRepository calendarEventRepository;

    public TodoQueryController(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    @Operation(
            summary = "Todo 목록 조회",
            description = "Todo 목록을 조회합니다. done 파라미터를 사용해 완료 여부로 필터링할 수 있습니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "조회 성공"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500", description = "서버 내부 오류"
            )
    })
    @GetMapping
    public ApiResponse<List<CalendarEvent>> findTodos(
            @Parameter(
                    description = "완료 여부 필터 (true: 완료, false: 미완료, 미전달 시 전체 조회)",
                    example = "true"
            )
            @RequestParam(required = false) Boolean done
    ) {
        if (done == null) {
            return ApiResponse.ok(calendarEventRepository.findAll());
        }

        return ApiResponse.ok(calendarEventRepository.findByIsDone(done));
    }
}