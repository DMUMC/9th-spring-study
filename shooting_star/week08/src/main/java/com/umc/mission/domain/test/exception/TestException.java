package com.umc.mission.domain.test.exception;

import com.umc.mission.global.apiPayload.code.BaseErrorCode;
import com.umc.mission.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}
