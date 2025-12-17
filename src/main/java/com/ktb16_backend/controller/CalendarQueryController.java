package com.ktb16_backend.controller;

import com.ktb16_backend.dto.CalendarEventResponse;
import com.ktb16_backend.service.CalendarQueryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarQueryController {

    private final CalendarQueryService calendarQueryService;

    public CalendarQueryController(CalendarQueryService calendarQueryService) {
        this.calendarQueryService = calendarQueryService;
    }

    // 전체 조회
    @GetMapping
    public List<CalendarEventResponse> findAll() {
        return calendarQueryService.findAll();
    }

    // 기간 조회 (월별/범위)
    @GetMapping("/range")
    public List<CalendarEventResponse> findByRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return calendarQueryService.findByRange(start, end);
    }
}