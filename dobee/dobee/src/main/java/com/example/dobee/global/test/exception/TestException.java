package com.example.dobee.global.test.exception;

import com.example.dobee.global.apiPayload.code.BaseErrorCode;
import com.example.dobee.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {

    public TestException(BaseErrorCode code) {
        super(code);
    }
}
