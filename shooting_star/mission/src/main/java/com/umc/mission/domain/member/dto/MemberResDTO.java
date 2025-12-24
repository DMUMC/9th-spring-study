package com.umc.mission.domain.member.dto;

import java.time.LocalDateTime;
import lombok.Builder;

public class MemberResDTO {
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ) {
    }

    // 로그인 응답 dto (회원의 기본키 id와 엑세스 토큰 응답)
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ) {
    }
}
