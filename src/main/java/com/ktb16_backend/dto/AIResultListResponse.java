package com.ktb16_backend.dto;

import com.ktb16_backend.domain.AIResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AIResultListResponse {

    private Long id;
    private String title;
    private String dateType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;

    public static AIResultListResponse from(AIResult entity) {
        return new AIResultListResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDateType(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getCreatedAt()
        );
    }
}