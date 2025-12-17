package com.ktb16_backend.dto;

import java.time.LocalDate;
import java.util.List;

public class AIResultResponse {

    public Long id;
    public String title;
    public String summary;
    public String category;
    public String dateType;

    public LocalDate startDate;
    public LocalDate endDate;
    public List<LocalDate> dates;
}