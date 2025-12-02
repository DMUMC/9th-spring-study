package com.umc.mission.domain.review.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewStatus {
    ACTIVE("활성화"),
    DELETED("삭제됨"),
    HIDDEN("숨김");

    private final String description;
}