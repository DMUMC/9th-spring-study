package com.example.dobee.domain.member.exception;

import com.example.dobee.global.apiPayload.code.BaseErrorCode;
import com.example.dobee.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
