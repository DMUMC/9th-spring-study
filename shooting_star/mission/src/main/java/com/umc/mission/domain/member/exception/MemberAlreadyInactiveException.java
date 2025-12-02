package com.umc.mission.domain.member.exception;

import com.umc.mission.domain.member.enums.MemberErrorCode;
import com.umc.mission.global.exception.CustomException;

public class MemberAlreadyInactiveException extends CustomException {
    public MemberAlreadyInactiveException() {
        super(MemberErrorCode.MEMBER_ALREADY_INACTIVE);
    }

    public MemberAlreadyInactiveException(String message) {
        super(MemberErrorCode.MEMBER_ALREADY_INACTIVE, message);
    }
}