package com.umc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("UMC 미션 관리 시스템 API")
                        .version("v1.0.0")
                        .description("UMC 3주차 미션 - 가게 리뷰 및 미션 도전 API"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:9098")
                                .description("로컬 서버")
                ));
    }
}
