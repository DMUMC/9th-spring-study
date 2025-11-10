package com.example.dobee.domain.review.exception;


import com.example.dobee.domain.review.enums.ReviewErrorCode;
import com.example.dobee.global.exception.CustomException;

public class ReviewNotFoundException extends CustomException {
    public ReviewNotFoundException() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }

    public ReviewNotFoundException(String message) {
        super(ReviewErrorCode.REVIEW_NOT_FOUND, message);
    }
}
