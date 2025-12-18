package com.ktb16_backend.service;

import com.ktb16_backend.domain.AIResult;
import com.ktb16_backend.domain.AIResultDate;
import com.ktb16_backend.dto.AIResultListResponse;
import com.ktb16_backend.dto.AIResultRequest;
import com.ktb16_backend.dto.AIResultResponse;
import com.ktb16_backend.repository.AIResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AIResultService {

    private final AIResultRepository aiResultRepository;

    public AIResultService(AIResultRepository aiResultRepository) {
        this.aiResultRepository = aiResultRepository;
    }

    public AIResultResponse save(AIResultRequest request) {

        AIResult aiResult = new AIResult();

        // 기본 필드 세팅
        aiResult.setTitle(request.title);
        aiResult.setSummary(request.summary);
        aiResult.setDateType(request.dateType);
        aiResult.setStartDate(request.startDate);
        aiResult.setEndDate(request.endDate);

        // 날짜 타입이 MULTIPLE 인 경우
        if ("MULTIPLE".equals(request.dateType) && request.dates != null) {
            request.dates.forEach(date ->
                    aiResult.getDates().add(new AIResultDate(aiResult, date))
            );
        }

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
}