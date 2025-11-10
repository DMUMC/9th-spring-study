package com.example.dobee.domain.review.exception;

import com.example.dobee.domain.review.enums.ReviewErrorCode;
import com.example.dobee.global.exception.CustomException;

public class InvalidRatingException extends CustomException {
    public InvalidRatingException() {
        super(ReviewErrorCode.INVALID_RATING);
    }

    public InvalidRatingException(String message) {
        super(ReviewErrorCode.INVALID_RATING, message);
    }
}
