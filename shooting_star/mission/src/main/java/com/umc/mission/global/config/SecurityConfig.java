package com.umc.mission.global.config;

import com.umc.mission.global.auth.AuthenticationEntryPointImpl;
import com.umc.mission.global.auth.CustomUserDetailService;
import com.umc.mission.global.auth.JwtAuthFilter;
import com.umc.mission.global.auth.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    private final String[] allowUris = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/error",
            "/sign-up",
            "/login"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests // http 요청에 대한 접근 권한을 설정
                        .requestMatchers(allowUris).permitAll() // 특정 url 패턴에 대한 접근 권한을 설정함.
                        .requestMatchers("/swagger-ui/index.html").hasRole("ADMIN") // 'ADMIN' 역할을 가진 사용자만 접근 가능하도록 제한
                        .anyRequest().authenticated()) // 그 외 모든 요청에 대해 인증을 요구.
//                .formLogin(form -> form
//                        .defaultSuccessUrl("/", true)
//                        .permitAll())
                .formLogin(AbstractHttpConfigurer::disable) // 폼 로그인 비활성화
                .addFilterBefore(jwtAuthFilter(),
                        UsernamePasswordAuthenticationFilter.class) // JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
                .csrf(AbstractHttpConfigurer::disable) // csrf 끄기
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 처리 url
                        .logoutSuccessUrl("/logout?logout") // 로그아웃 성공시 리다이렉트 url
                        .permitAll())
                .exceptionHandling((exception) -> exception.authenticationEntryPoint(authenticationEntryPoint()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailService);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }
}