package com.example.umc9th.domain.mission.exception;

import com.example.umc9th.global.ApiPayload.code.BaseErrorCode;
import com.example.umc9th.global.ApiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}