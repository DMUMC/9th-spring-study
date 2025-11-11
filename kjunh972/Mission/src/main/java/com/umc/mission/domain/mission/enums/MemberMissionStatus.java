package com.umc.mission.domain.mission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberMissionStatus {
    ONGOING("진행중"),
    COMPLETED("완료"),
    FAILED("실패");

    private final String description;
}