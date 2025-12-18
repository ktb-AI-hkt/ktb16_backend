package com.ktb16_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공통 API 응답 포맷")
public class ApiResponse<T> {

    @Schema(
            description = "요청 성공 여부",
            example = "true"
    )
    public boolean success;

    @Schema(
            description = "응답 데이터 (성공 시 존재)"
    )
    public T data;

    @Schema(
            description = "에러 메시지 (실패 시 존재)",
            example = "null"
    )
    public String error;

    private ApiResponse(boolean success, T data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> error(String error) {
        return new ApiResponse<>(false, null, error);
    }
}