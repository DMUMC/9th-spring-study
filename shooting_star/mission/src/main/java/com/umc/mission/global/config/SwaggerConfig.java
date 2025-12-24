package com.umc.mission.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
    @Bean
    public OpenAPI swagger(){
        // Info 객체: swagger ui의 제목, 설명, 버전 등 기본 정보를 설정
        Info info = new Info()
                .title("Project").
                description("Project Swagger")
                .version("0.0.1");

        // JWT 토큰 헤더 방식
        String securityScheme = "JWT TOKEN";

        // SecurityRequirement 객체: api 앤드포인트마다 어떤 보안 이름표가 필요한지 정의
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(securityScheme); // 해당 api는 JWT TOKEN 이라는 이름표가 필요하다는 요구사항을 추가

        // Components 객체: swagger ui의 구성 요소를 정의함(보안 스키마 등)
        Components components = new Components()
                // JWT TOKEN 이라는 이름표가 실제로 어떻게 동작하는지 정의함
                .addSecuritySchemes(securityScheme, new SecurityScheme()
                        .name(securityScheme) // 단순 식별용 보안 스키마 이름
                        .type(SecurityScheme.Type.HTTP) // 보안 타입: HTTP를 사용
                        .scheme("Bearer") // HTTP 인증 스키마: Bearer 방식을 사용
                        .bearerFormat("JWT")); // 토큰 형식: "JWT"
        return new OpenAPI()
                .info(info) // 기본 정보를 설정함
                .addServersItem(new Server().url("/")) // api 서버의 기본 url을 설정함;
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}












