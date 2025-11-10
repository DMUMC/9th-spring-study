package com.example.dobee.domain.member.exception;

import com.example.dobee.domain.member.enums.MemberErrorCode;
import com.example.dobee.global.exception.CustomException;

public class MemberAlreadyInactiveException extends CustomException {
    public MemberAlreadyInactiveException() {
        super(MemberErrorCode.MEMBER_ALREADY_INACTIVE);
    }

    public MemberAlreadyInactiveException(String message) {
        super(MemberErrorCode.MEMBER_ALREADY_INACTIVE, message);
    }
}