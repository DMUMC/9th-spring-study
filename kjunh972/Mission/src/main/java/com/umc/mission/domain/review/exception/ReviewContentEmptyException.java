package com.umc.mission.domain.review.exception;

import com.umc.mission.domain.review.enums.ReviewErrorCode;
import com.umc.mission.global.exception.CustomException;

public class ReviewContentEmptyException extends CustomException {
    public ReviewContentEmptyException() {
        super(ReviewErrorCode.REVIEW_CONTENT_EMPTY);
    }

    public ReviewContentEmptyException(String message) {
        super(ReviewErrorCode.REVIEW_CONTENT_EMPTY, message);
    }
}