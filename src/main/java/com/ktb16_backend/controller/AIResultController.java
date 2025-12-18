package com.ktb16_backend.controller;

import com.ktb16_backend.dto.AIResultListResponse;
import com.ktb16_backend.dto.AIResultRequest;
import com.ktb16_backend.dto.AIResultResponse;
import com.ktb16_backend.service.AIResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(
            summary = "AI 변환 기록 목록 조회",
            description = "저장된 AI 분석 결과 목록을 최신순으로 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "변환 기록 목록 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping
    public List<AIResultListResponse> getAll() {
        return aiResultService.findAll();
    }

    @Operation(
            summary = "AI 변환 기록 상세 조회",
            description = "선택한 AI 분석 결과의 상세 정보를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "변환 기록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 결과 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/{id}")
    public AIResultResponse getOne(@PathVariable Long id) {
        return aiResultService.findById(id);
    }
}