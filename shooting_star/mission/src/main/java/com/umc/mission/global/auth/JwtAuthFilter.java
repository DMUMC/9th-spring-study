package com.umc.mission.global.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.mission.global.apiPayload.ApiResponse;
import com.umc.mission.global.apiPayload.code.GeneralErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // 토큰 가져오기
        String token = request.getHeader("Authorization");

        // token이 없거나 Bearer가 아니면 넘기기
        if(token == null || !token.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        // Bearer이면 추출
        token = token.replace("Bearer ", "");

        // Access token 검증하기
        if(jwtUtil.isValid(token)){
            // 토큰에서 이메일 추출
            String email = jwtUtil.getEmail(token);
            // 인증 객체 생성: 이메일로 찾아온 뒤, 인증 객체 생성
            UserDetails user = customUserDetailService.loadUserByUsername(email);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );

            // 인증 완료 후 SecurityContextHolder에 넣기
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
