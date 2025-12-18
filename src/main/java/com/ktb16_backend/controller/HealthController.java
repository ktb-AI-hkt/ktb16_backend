package com.ktb16_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health", description = "서버 상태 확인 API")
public class HealthController {

    @Operation(
            summary = "서버 상태 체크",
            description = "서버가 정상적으로 동작 중인지 확인합니다."
    )
    @ApiResponse(responseCode = "200", description = "서버 정상 동작")
    @GetMapping("api/health")
    public String health() {
        return "OK";
    }
}