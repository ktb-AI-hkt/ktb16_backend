package com.ktb16_backend.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktb16_backend.dto.AISummary;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AISummaryConverter implements AttributeConverter<AISummary, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(AISummary attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new IllegalArgumentException("AISummary DB 저장 실패", e);
        }
    }

    @Override
    public AISummary convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, AISummary.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("AISummary DB 로드 실패", e);
        }
    }
}