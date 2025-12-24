package com.umc.mission.domain.auth.session.controller;

import com.umc.mission.domain.auth.session.dto.LoginRequest;
import com.umc.mission.domain.auth.session.dto.SignupRequest;
import com.umc.mission.domain.auth.session.service.SessionAuthService;
import com.umc.mission.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session/auth")
@RequiredArgsConstructor
@Tag(name = "Session Auth", description = "Session 기반 인증 API")
public class SessionAuthController {

    private final SessionAuthService sessionAuthService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "Session 방식 회원가입")
    public ApiResponse<Void> signup(@Valid @RequestBody SignupRequest request) {
        sessionAuthService.signup(request);
        return ApiResponse.ok();
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "Session 방식 로그인")
    public ApiResponse<Void> login(@Valid @RequestBody LoginRequest request) {
        sessionAuthService.login(request);
        return ApiResponse.ok();
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "Session 방식 로그아웃")
    public ApiResponse<Void> logout(HttpSession session) {
        session.invalidate();
        sessionAuthService.logout();
        return ApiResponse.ok();
    }

    @GetMapping("/test")
    @Operation(summary = "인증 테스트", description = "인증된 사용자만 접근 가능")
    public ApiResponse<String> test() {
        return ApiResponse.ok("인증된 사용자입니다");
    }
}