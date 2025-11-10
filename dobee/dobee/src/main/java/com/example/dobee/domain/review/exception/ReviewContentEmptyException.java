package com.example.dobee.domain.review.exception;

import com.example.dobee.domain.review.enums.ReviewErrorCode;
import com.example.dobee.global.exception.CustomException;

public class ReviewContentEmptyException extends CustomException {
    public ReviewContentEmptyException() {
        super(ReviewErrorCode.REVIEW_CONTENT_EMPTY);
    }

    public ReviewContentEmptyException(String message) {
        super(ReviewErrorCode.REVIEW_CONTENT_EMPTY, message);
    }
}
