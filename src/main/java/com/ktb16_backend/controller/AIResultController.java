package com.ktb16_backend.controller;

import com.ktb16_backend.dto.AIResultRequest;
import com.ktb16_backend.dto.AIResultResponse;
import com.ktb16_backend.service.AIResultService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai-results")
public class AIResultController {

    private final AIResultService aiResultService;

    public AIResultController(AIResultService aiResultService) {
        this.aiResultService = aiResultService;
    }

    @PostMapping
    public AIResultResponse save(@RequestBody AIResultRequest request) {
        return aiResultService.save(request);
    }
}