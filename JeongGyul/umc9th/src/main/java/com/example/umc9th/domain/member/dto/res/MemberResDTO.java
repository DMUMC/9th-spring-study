package com.example.umc9th.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Builder
    @Getter
    public static class WriterDTO {
        private Long memberId;
        private String nickname;
    }
}