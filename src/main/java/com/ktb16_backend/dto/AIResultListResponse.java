package com.ktb16_backend.dto;

import com.ktb16_backend.domain.AIResult;
import com.ktb16_backend.domain.AIResultDate;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AIResultListResponse {

    private Long id;
    private String title;
    private String summary;
    private String dateType;

    private LocalDate startDate;
    private LocalDate endDate;

    private List<LocalDate> dates;
    private LocalDateTime createdAt;

    public static AIResultListResponse from(AIResult entity) {
        AIResultListResponse response = new AIResultListResponse();

        response.id = entity.getId();
        response.title = entity.getTitle();
        response.summary = entity.getSummary();
        response.dateType = entity.getDateType();
        response.createdAt = entity.getCreatedAt();

        response.dates = entity.getDates()
                .stream()
                .map(AIResultDate::getDate)
                .sorted()
                .toList();

        if (!response.dates.isEmpty()) {
            response.startDate = response.dates.get(0);
            response.endDate = response.dates.get(response.dates.size() - 1);
        }

        return response;
    }
}