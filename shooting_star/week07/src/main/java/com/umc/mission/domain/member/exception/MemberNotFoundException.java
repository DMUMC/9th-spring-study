package com.umc.mission.domain.member.exception;

import com.umc.mission.domain.member.enums.MemberErrorCode;
import com.umc.mission.global.exception.CustomException;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException() {
        super(MemberErrorCode.MEMBER_NOT_FOUND);
    }

    public MemberNotFoundException(String message) {
        super(MemberErrorCode.MEMBER_NOT_FOUND, message);
    }
}