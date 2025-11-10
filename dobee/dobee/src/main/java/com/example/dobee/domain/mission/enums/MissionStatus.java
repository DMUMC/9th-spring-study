package com.example.dobee.domain.mission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionStatus {
    ACTIVE("활성화"),
    INACTIVE("비활성화"),
    EXPIRED("만료");

    private final String description;
}
