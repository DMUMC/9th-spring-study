package com.example.dobee.domain.review.exception;

import com.example.dobee.domain.review.enums.ReviewErrorCode;
import com.example.dobee.global.exception.CustomException;

public class StoreNotFoundException extends CustomException {
    public StoreNotFoundException() {
        super(ReviewErrorCode.STORE_NOT_FOUND);
    }

    public StoreNotFoundException(String message) {
        super(ReviewErrorCode.STORE_NOT_FOUND, message);
    }
}
