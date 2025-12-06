package com.umc.mission.global.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    private final Duration accessExpiration;

    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpiration
    ){
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    // access token 생성
    public String createAccessToken(
            CustomUserDetails user
    ){
        return createToken(user, accessExpiration);
    }

    /** 토큰에서 이메일 가져오기
     * @param token 유저 정보를 추출할 토큰
     * @return 유저 이메일을 토큰에서 추출합니다
     */
    public String getEmail(String token){
        try{
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException e){
            return null;
        }
    }

    public boolean isValid(String token){
        try{
            getClaims(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }

    // 토큰 생성
    private String createToken(CustomUserDetails user, Duration accessExpiration) {
        Instant now = Instant.now();

        // 인가 정보(권한)
        String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(user.getUsername()) // User 이메일을 Subject로
                .claim("role", authorities)
                .claim("email", user.getUsername())
                .issuedAt(Date.from(now)) // 언제 발급했는지
                .expiration(Date.from(now.plus(accessExpiration))) // 언제까지 유효할지
                .signWith(secretKey) // sign할 key
                .compact();
    }
    private Jws<Claims> getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }
}
