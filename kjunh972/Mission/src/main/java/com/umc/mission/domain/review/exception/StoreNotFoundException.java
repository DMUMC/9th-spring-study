package com.umc.mission.domain.review.exception;

import com.umc.mission.domain.review.enums.ReviewErrorCode;
import com.umc.mission.global.exception.CustomException;

public class StoreNotFoundException extends CustomException {
    public StoreNotFoundException() {
        super(ReviewErrorCode.STORE_NOT_FOUND);
    }

    public StoreNotFoundException(String message) {
        super(ReviewErrorCode.STORE_NOT_FOUND, message);
    }
}