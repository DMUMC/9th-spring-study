package com.example.dobee.domain.member.exception;

import com.example.dobee.domain.member.enums.MemberErrorCode;
import com.example.dobee.global.exception.CustomException;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException() {
        super(MemberErrorCode.MEMBER_NOT_FOUND);
    }

    public MemberNotFoundException(String message) {
        super(MemberErrorCode.MEMBER_NOT_FOUND, message);
    }
}
