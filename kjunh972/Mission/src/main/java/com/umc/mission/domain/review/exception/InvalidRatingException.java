package com.umc.mission.domain.review.exception;

import com.umc.mission.domain.review.enums.ReviewErrorCode;
import com.umc.mission.global.exception.CustomException;

public class InvalidRatingException extends CustomException {
    public InvalidRatingException() {
        super(ReviewErrorCode.INVALID_RATING);
    }

    public InvalidRatingException(String message) {
        super(ReviewErrorCode.INVALID_RATING, message);
    }
}