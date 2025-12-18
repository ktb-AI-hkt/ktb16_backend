package com.ktb16_backend.dto;

import com.ktb16_backend.domain.AIResult;
import com.ktb16_backend.domain.AIResultDate;

import java.time.LocalDate;
import java.util.List;

public class AIResultResponse {

    public Long id;
    public String title;
    public String summary;
    public String dateType;

    public LocalDate startDate;
    public LocalDate endDate;

    public List<LocalDate> dates;

    public static AIResultResponse from(AIResult entity) {
        AIResultResponse response = new AIResultResponse();

        response.id = entity.getId();
        response.title = entity.getTitle();
        response.summary = entity.getSummary();
        response.dateType = entity.getDateType();

        if ("NONE".equals(entity.getDateType())) {
            response.dates = List.of();
            response.startDate = null;
            response.endDate = null;
            return response;
        }

        response.dates = entity.getDates()
                .stream()
                .map(AIResultDate::getDate)
                .sorted()
                .toList();

        if (!response.dates.isEmpty()) {
            response.startDate = response.dates.get(0);
            response.endDate = response.dates.get(response.dates.size() - 1);
        } else {
            response.startDate = null;
            response.endDate = null;
        }

        return response;
    }
}