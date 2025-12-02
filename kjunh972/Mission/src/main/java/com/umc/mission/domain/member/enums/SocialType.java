package com.umc.mission.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialType {
    GENERAL("General"),
    KAKAO("Kakao");

    private final String description;
}