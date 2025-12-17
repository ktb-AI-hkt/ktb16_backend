package com.ktb16_backend.controller;

import com.ktb16_backend.domain.CalendarEvent;
import com.ktb16_backend.dto.ApiResponse;
import com.ktb16_backend.repository.CalendarEventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoQueryController {

    private final CalendarEventRepository calendarEventRepository;

    public TodoQueryController(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    @GetMapping
    public ApiResponse<List<CalendarEvent>> findTodos(
            @RequestParam(required = false) Boolean done
    ) {
        if (done == null) {
            return ApiResponse.ok(calendarEventRepository.findAll());
        }

        return ApiResponse.ok(calendarEventRepository.findByIsDone(done));
    }
}