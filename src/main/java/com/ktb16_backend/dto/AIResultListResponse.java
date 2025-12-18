package com.ktb16_backend.dto;

import com.ktb16_backend.domain.AIResult;
import com.ktb16_backend.domain.AIResultDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AIResultListResponse {

    private Long id;
    private String title;

    private String what;

    private LocalDate displayDate;

    private String dateType;
    private LocalDateTime createdAt;

    public static AIResultListResponse from(AIResult entity) {
        return new AIResultListResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getSummary().getWhat(),
                resolveDisplayDate(entity),
                entity.getDateType(),
                entity.getCreatedAt()
        );
    }

    private static LocalDate resolveDisplayDate(AIResult entity) {
        return switch (entity.getDateType()) {
            case "SINGLE", "RANGE" -> entity.getStartDate();
            case "MULTIPLE" -> entity.getDates()
                    .stream()
                    .map(AIResultDate::getDate)
                    .sorted()
                    .findFirst()
                    .orElse(null);
            default -> null;
        };
    }
}