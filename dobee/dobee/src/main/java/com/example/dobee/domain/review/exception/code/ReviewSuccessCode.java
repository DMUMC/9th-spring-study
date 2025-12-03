package com.example.dobee.domain.review.exception.code;

import com.example.dobee.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    // Review Success Code
    GET_MY_REVIEWS_SUCCESS(HttpStatus.OK, "REVIEW_001", "내가 작성한 리뷰 조회가 완료되었습니다."),
    REVIEW_ADD_SUCCESS(HttpStatus.CREATED, "REVIEW_002", "리뷰 작성이 완료되었습니다."),
    REIVEW_FOUND(HttpStatus.FOUND, "REVIEW_003", "리뷰 조회가 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
