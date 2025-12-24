package com.umc.mission.domain.member.dto;

import com.umc.mission.domain.member.enums.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionStats {
        private Long total;
        private Long ongoing;
        private Long completed;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberCreateDTO {
        private SocialType socialAttr;
        private String socialId;
        private String photo;
        private String name;
    }
}
