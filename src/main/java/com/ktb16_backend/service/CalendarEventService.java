package com.ktb16_backend.service;

import com.ktb16_backend.domain.AIResult;
import com.ktb16_backend.domain.AIResultDate;
import com.ktb16_backend.domain.CalendarEvent;
import com.ktb16_backend.dto.CalendarSaveResponse;
import com.ktb16_backend.exception.NotFoundException;
import com.ktb16_backend.repository.AIResultRepository;
import com.ktb16_backend.repository.CalendarEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CalendarEventService {

    private final AIResultRepository aiResultRepository;
    private final CalendarEventRepository calendarEventRepository;

    public CalendarEventService(
            AIResultRepository aiResultRepository,
            CalendarEventRepository calendarEventRepository
    ) {
        this.aiResultRepository = aiResultRepository;
        this.calendarEventRepository = calendarEventRepository;
    }

    public List<CalendarSaveResponse> saveFromAIResult(Long aiResultId) {

        AIResult aiResult = aiResultRepository.findById(aiResultId)
                .orElseThrow(() -> new NotFoundException("AI_RESULT not found"));

        List<CalendarEvent> events = new ArrayList<>();

        switch (aiResult.getDateType()) {

            case "SINGLE" -> {
                events.add(
                        CalendarEvent.create(
                                aiResult,
                                aiResult.getTitle(),
                                aiResult.getStartDate(),
                                aiResult.getStartDate()
                        )
                );
            }

            case "RANGE" -> {
                events.add(
                        CalendarEvent.create(
                                aiResult,
                                aiResult.getTitle(),
                                aiResult.getStartDate(),
                                aiResult.getEndDate()
                        )
                );
            }

            case "MULTIPLE" -> {
                for (AIResultDate date : aiResult.getDates()) {
                    events.add(
                            CalendarEvent.create(
                                    aiResult,
                                    aiResult.getTitle(),
                                    date.getDate(),
                                    date.getDate()
                            )
                    );
                }
            }

            default -> throw new IllegalStateException("Unknown dateType");
        }

        List<CalendarEvent> savedEvents = calendarEventRepository.saveAll(events);

        return savedEvents.stream()
                .map(this::toResponse)
                .toList();
    }

    private CalendarSaveResponse toResponse(CalendarEvent event) {
        CalendarSaveResponse response = new CalendarSaveResponse();
        response.calendarEventId = event.getId();
        response.title = event.getTitle();
        response.startDate = event.getStartDate();
        response.endDate = event.getEndDate();
        return response;
    }
}