package com.umc.mission.domain.review.exception.code;

import com.umc.mission.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    FOUND(
            HttpStatus.FOUND,
            "REVIEW200_1",
            "리뷰가 성공적으로 조회되었습니다."
    ),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
