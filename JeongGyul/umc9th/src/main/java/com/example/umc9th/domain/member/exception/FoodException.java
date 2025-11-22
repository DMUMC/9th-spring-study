package com.example.umc9th.domain.member.exception;

import com.example.umc9th.global.ApiPayload.code.BaseErrorCode;
import com.example.umc9th.global.ApiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
