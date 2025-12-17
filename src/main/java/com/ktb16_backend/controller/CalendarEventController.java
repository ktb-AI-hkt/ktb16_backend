package com.ktb16_backend.controller;

import com.ktb16_backend.dto.ApiResponse;
import com.ktb16_backend.dto.CalendarSaveRequest;
import com.ktb16_backend.dto.CalendarSaveResponse;
import com.ktb16_backend.service.CalendarEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarEventController {

    private final CalendarEventService calendarEventService;

    public CalendarEventController(CalendarEventService calendarEventService) {
        this.calendarEventService = calendarEventService;
    }

    @PostMapping("/save")
    public ApiResponse<List<CalendarSaveResponse>> save(
            @RequestBody CalendarSaveRequest request
    ) {
        return ApiResponse.ok(
                calendarEventService.saveFromAIResult(request.aiResultId)
        );
    }
}