package com.example.dobee.domain.member.enums;

import com.example.dobee.global.code.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements ResponseCode {

    MEMBER_NOT_FOUND("MB001", "Member not found"),
    MEMBER_ALREADY_INACTIVE("MB002", "Member is already inactive"),
    MEMBER_DELETE_FAILED("MB003", "Member deletion failed"),
    MEMBER_UPDATE_FAILED("MB004", "Member update failed"),
    INVALID_MEMBER_STATUS("MB005", "Invalid member status");

    private final String statusCode;
    private final String message;
}
