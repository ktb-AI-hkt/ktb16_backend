package com.ktb16_backend.dto;

import java.time.LocalDate;
import java.util.List;

public class AIResultRequest {

    public String title;
    public String summary;
    public String category;
    public String dateType; // SINGLE / RANGE / MULTIPLE

    public LocalDate startDate;
    public LocalDate endDate;

    // MULTIPLE일 때만 사용
    public List<LocalDate> dates;

    public String rawText;
}