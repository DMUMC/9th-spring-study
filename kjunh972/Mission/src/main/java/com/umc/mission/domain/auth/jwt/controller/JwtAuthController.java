package com.umc.mission.domain.auth.jwt.controller;

import com.umc.mission.domain.auth.jwt.dto.JwtLoginDto;
import com.umc.mission.domain.auth.jwt.dto.JwtSignupRequest;
import com.umc.mission.domain.auth.jwt.service.JwtAuthService;
import com.umc.mission.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt/auth")
@RequiredArgsConstructor
@Tag(name = "JWT Auth", description = "JWT 기반 인증 API")
public class JwtAuthController {

    private final JwtAuthService jwtAuthService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "JWT 방식 회원가입")
    public ApiResponse<Void> signup(@Valid @RequestBody JwtSignupRequest request) {
        jwtAuthService.signup(request);
        return ApiResponse.ok();
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "JWT 방식 로그인 - 토큰 발급")
    public ApiResponse<JwtLoginDto.Response> login(@Valid @RequestBody JwtLoginDto.Request request) {
        JwtLoginDto.Response response = jwtAuthService.login(request);
        return ApiResponse.ok(response);
    }

    @GetMapping("/test")
    @Operation(summary = "인증 테스트", description = "JWT 토큰으로 인증된 사용자만 접근 가능")
    public ApiResponse<String> test() {
        return ApiResponse.ok("JWT 인증된 사용자입니다");
    }
}