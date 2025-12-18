package com.ktb16_backend.dto;

import java.time.LocalDate;
import java.util.List;

public class AIResultDetailResponse {

    public Long id;
    public String title;

    public AISummary summary;
    public String rawText;

    public String dateType;
    public LocalDate startDate;
    public LocalDate endDate;

    public List<LocalDate> dates;
}