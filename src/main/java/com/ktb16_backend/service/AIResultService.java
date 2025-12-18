package com.ktb16_backend.service;

import com.ktb16_backend.domain.AIResult;
import com.ktb16_backend.domain.AIResultDate;
import com.ktb16_backend.dto.AIResultListResponse;
import com.ktb16_backend.dto.AIResultRequest;
import com.ktb16_backend.dto.AIResultResponse;
import com.ktb16_backend.repository.AIResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AIResultService {

    private final AIResultRepository aiResultRepository;

    public AIResultService(AIResultRepository aiResultRepository) {
        this.aiResultRepository = aiResultRepository;
    }

    public AIResultResponse save(AIResultRequest request) {

        String dateType = request.dateType.toUpperCase();
        request.dateType = dateType;

        AIResult aiResult = new AIResult();
        aiResult.setTitle(request.title);
        aiResult.setSummary(request.summary);
        aiResult.setDateType(dateType);

        if ("NONE".equals(dateType)) {
            // 날짜 관련 로직 전부 스킵
            AIResult saved = aiResultRepository.save(aiResult);
            return AIResultResponse.from(saved);
        }

        if ("RANGE".equals(dateType)
                && (request.dates == null || request.dates.isEmpty())
                && request.startDate != null
                && request.endDate != null) {

            request.dates = List.of(
                    request.startDate,
                    request.endDate
            );
        }

        if ("SINGLE".equals(dateType)) {
            if (request.dates == null || request.dates.size() != 1) {
                throw new IllegalArgumentException(
                        "SINGLE dateType requires exactly one date"
                );
            }
        }

        if ("MULTIPLE".equals(dateType)) {
            if (request.dates == null || request.dates.size() < 2) {
                throw new IllegalArgumentException(
                        "MULTIPLE dateType requires two or more dates"
                );
            }
        }

        List<LocalDate> sortedDates = request.dates.stream()
                .sorted()
                .toList();

        for (LocalDate date : sortedDates) {
            aiResult.getDates().add(new AIResultDate(aiResult, date));
        }

        aiResult.setStartDate(sortedDates.get(0));
        aiResult.setEndDate(sortedDates.get(sortedDates.size() - 1));

        AIResult saved = aiResultRepository.save(aiResult);
        return AIResultResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public List<AIResultListResponse> findAll() {
        return aiResultRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(AIResultListResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public AIResultResponse findById(Long id) {
        AIResult aiResult = aiResultRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("AIResult not found. id=" + id)
                );

        return AIResultResponse.from(aiResult);
    }

    public void delete(Long id) {
        AIResult aiResult = aiResultRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("AIResult not found. id=" + id)
                );

        aiResultRepository.delete(aiResult);
    }
}