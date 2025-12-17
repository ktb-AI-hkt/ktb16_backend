package com.ktb16_backend.controller;

import com.ktb16_backend.domain.CalendarEvent;
import com.ktb16_backend.dto.ApiResponse;
import com.ktb16_backend.exception.NotFoundException;
import com.ktb16_backend.repository.CalendarEventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class TodoCommandController {

    private final CalendarEventRepository calendarEventRepository;

    public TodoCommandController(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    @PatchMapping("/{id}/toggle")
    public ApiResponse<Boolean> toggle(@PathVariable Long id) {

        CalendarEvent event = calendarEventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));

        event.setDone(!event.isDone());

        return ApiResponse.ok(event.isDone());
    }

    @PatchMapping("/{id}/memo")
    public ApiResponse<Void> updateMemo(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        CalendarEvent event = calendarEventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));

        event.setMemo(body.get("memo"));

        return ApiResponse.ok(null);
    }
}