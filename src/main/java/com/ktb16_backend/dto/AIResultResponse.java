package com.ktb16_backend.dto;

import com.ktb16_backend.domain.AIResult;

import java.time.LocalDate;
import java.util.List;

public class AIResultResponse {

    public Long id;
    public String title;

    public AISummary summary;

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

        // 파생 데이터는 Entity 기준
        response.startDate = entity.getStartDate();
        response.endDate = entity.getEndDate();

        return response;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }
}