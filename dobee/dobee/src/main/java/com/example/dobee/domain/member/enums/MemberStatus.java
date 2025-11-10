package com.example.dobee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberStatus {
    ACTIVE("활성화"),
    INACTIVE("비활성화");

    private final String description;
}
