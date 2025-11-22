package com.example.umc9th.domain.test.exception;

import com.example.umc9th.global.ApiPayload.code.BaseErrorCode;
import com.example.umc9th.global.ApiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}