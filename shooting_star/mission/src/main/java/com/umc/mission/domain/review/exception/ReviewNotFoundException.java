package com.umc.mission.domain.review.exception;

import com.umc.mission.domain.review.enums.ReviewErrorCode;
import com.umc.mission.global.exception.CustomException;

public class ReviewNotFoundException extends CustomException {
    public ReviewNotFoundException() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }

    public ReviewNotFoundException(String message) {
        super(ReviewErrorCode.REVIEW_NOT_FOUND, message);
    }
}