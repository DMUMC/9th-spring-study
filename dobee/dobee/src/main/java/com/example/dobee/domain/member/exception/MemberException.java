package com.example.dobee.domain.member.exception;

import com.example.dobee.global.apiPayload.code.BaseErrorCode;
import com.example.dobee.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}
