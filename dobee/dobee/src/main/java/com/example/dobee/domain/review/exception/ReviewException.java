package com.example.dobee.domain.review.exception;

import com.example.dobee.global.apiPayload.code.BaseErrorCode;
import com.example.dobee.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
