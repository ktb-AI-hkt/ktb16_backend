package com.ktb16_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AI Calendar API")
                        .description("사진 → AI → 일정 추출 및 캘린더/To-Do 관리 API")
                        .version("v1.0.0"));
    }
}