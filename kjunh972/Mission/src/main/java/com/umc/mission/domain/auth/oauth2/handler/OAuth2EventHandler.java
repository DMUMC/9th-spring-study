package com.umc.mission.domain.auth.oauth2.handler;

import com.umc.mission.domain.auth.oauth2.dto.KakaoOAuth2UserInfo;
import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.enums.SocialType;
import com.umc.mission.domain.member.service.MemberService;
import com.umc.mission.global.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2EventHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    @Value("${app.oauth2.redirect-uri}")
    private String redirectUri;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        log.info("OAuth2 Success - attributes: {}", oAuth2User.getAttributes());

        KakaoOAuth2UserInfo userInfo = new KakaoOAuth2UserInfo(oAuth2User.getAttributes());

        Member member = memberService.createOrUpdateMember(
                SocialType.KAKAO,
                userInfo.getSocialId(),
                userInfo.getProfileImage(),
                userInfo.getNickname()
        );

        String token = jwtUtil.generateToken(member.getEmail());

        String targetUrl = UriComponentsBuilder.fromUriString(redirectUri)
                .queryParam("token", token)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}