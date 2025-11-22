package com.example.umc9th.domain.store.exception;

import com.example.umc9th.global.ApiPayload.code.BaseErrorCode;
import com.example.umc9th.global.ApiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}