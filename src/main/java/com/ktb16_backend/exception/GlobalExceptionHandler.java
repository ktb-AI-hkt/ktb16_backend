package com.ktb16_backend.exception;

import com.ktb16_backend.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest request) {

        String uri = request.getRequestURI();

        // Swagger / OpenAPI 요청은 예외 처리에서 제외
        if (uri.startsWith("/v3/api-docs")
                || uri.startsWith("/swagger-ui")
                || uri.startsWith("/swagger-ui.html")) {
            return null;
        }

        return ApiResponse.error("Internal Server Error");
    }
}