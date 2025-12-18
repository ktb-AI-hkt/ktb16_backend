package com.ktb16_backend.service;

import com.ktb16_backend.domain.CalendarEvent;
import com.ktb16_backend.dto.CalendarEventResponse;
import com.ktb16_backend.repository.CalendarEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CalendarQueryService {

    private final CalendarEventRepository calendarEventRepository;

    public CalendarQueryService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    public List<CalendarEventResponse> findAll() {
        return calendarEventRepository.findAllByOrderByStartDateAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<CalendarEventResponse> findByRange(
            LocalDate start,
            LocalDate end
    ) {
        return calendarEventRepository
                .findByStartDateBetweenOrderByStartDateAsc(start, end)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private CalendarEventResponse toResponse(CalendarEvent event) {
        CalendarEventResponse res = new CalendarEventResponse();
        res.id = event.getId();
        res.title = event.getTitle();
        res.startDate = event.getStartDate();
        res.endDate = event.getEndDate();
        return res;
    }
}