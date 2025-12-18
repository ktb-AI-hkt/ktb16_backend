package com.ktb16_backend.service;

import com.ktb16_backend.domain.AIResult;
import com.ktb16_backend.domain.AIResultDate;
import com.ktb16_backend.dto.AIResultRequest;
import com.ktb16_backend.dto.AIResultResponse;
import com.ktb16_backend.repository.AIResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

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
        aiResult.setCategory(request.category);
        aiResult.setDateType(request.dateType);
        aiResult.setStartDate(request.startDate);
        aiResult.setEndDate(request.endDate);
        aiResult.setRawText(request.rawText);

        // MULTIPLE 날짜 처리
        if ("MULTIPLE".equals(request.dateType) && request.dates != null) {
            request.dates.forEach(aiResult::addDate);
        }

        AIResult saved = aiResultRepository.save(aiResult);

        return toResponse(saved);
    }

    // Entity → Response
    private AIResultResponse toResponse(AIResult aiResult) {
        AIResultResponse res = new AIResultResponse();

        res.id = aiResult.getId();
        res.title = aiResult.getTitle();
        res.summary = aiResult.getSummary();
        res.category = aiResult.getCategory();
        res.dateType = aiResult.getDateType();
        res.startDate = aiResult.getStartDate();
        res.endDate = aiResult.getEndDate();

        res.dates = aiResult.getDates()
                .stream()
                .map(AIResultDate::getDate)
                .collect(Collectors.toList());

        return res;
    }
}