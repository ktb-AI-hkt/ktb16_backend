package com.ktb16_backend.dto;

import com.ktb16_backend.domain.AIResult;
import com.ktb16_backend.domain.AIResultDate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

        // 타입별 날짜 매핑 (의미 기준)
        switch (entity.getDateType()) {
            case "SINGLE" -> {
                response.startDate = entity.getStartDate();
            }
            case "RANGE" -> {
                response.startDate = entity.getStartDate();
                response.endDate = entity.getEndDate();
            }
            case "MULTIPLE" -> {
                if (entity.getDates() != null && !entity.getDates().isEmpty()) {
                    response.dates = entity.getDates()
                            .stream()
                            .map(AIResultDate::getDate)
                            .collect(Collectors.toList());
                }
            }
        }

        return response;
    }
}