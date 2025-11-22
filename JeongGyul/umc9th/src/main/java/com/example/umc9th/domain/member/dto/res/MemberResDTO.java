package com.example.umc9th.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    @Getter
    public static class WriterDTO {
        private Long memberId;
        private String nickname;
    }

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {}
}