package com.example.dobee.domain.store.exception;

import com.example.dobee.global.apiPayload.code.BaseErrorCode;
import com.example.dobee.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
