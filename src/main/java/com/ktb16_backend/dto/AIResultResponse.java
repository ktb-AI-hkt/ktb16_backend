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

        switch (entity.getDateType()) {

            case "SINGLE" -> {
                LocalDate date = entity.getStartDate();
                response.startDate = date;
                response.endDate = date;
                response.dates = List.of(date);
            }

            case "RANGE" -> {
                response.startDate = entity.getStartDate();
                response.endDate = entity.getEndDate();
                response.dates = List.of(
                        entity.getStartDate(),
                        entity.getEndDate()
                );
            }

            case "MULTIPLE" -> {
                response.dates = entity.getDates()
                        .stream()
                        .map(AIResultDate::getDate)
                        .sorted()
                        .toList();

                response.startDate = response.dates.get(0);
                response.endDate = response.dates.get(response.dates.size() - 1);
            }
        }
        return response;
    }
}