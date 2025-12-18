package com.ktb16_backend.controller;

import com.ktb16_backend.dto.AIResultRequest;
import com.ktb16_backend.dto.AIResultResponse;
import com.ktb16_backend.service.AIResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai-results")
@Tag(name = "AI Result", description = "AI 일정 추출 결과 API")
public class AIResultController {

    private final AIResultService aiResultService;

    public AIResultController(AIResultService aiResultService) {
        this.aiResultService = aiResultService;
    }

    @Operation(
            summary = "AI 분석 결과 저장",
            description = "사진 기반 AI 분석 결과를 저장하고, 일정 정보 요약을 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "AI 결과 저장 성공"),
            @ApiResponse(responseCode = "400", description = "요청 값 오류"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping
    public AIResultResponse save(@RequestBody AIResultRequest request) {
        return aiResultService.save(request);
    }
}