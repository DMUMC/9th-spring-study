package com.umc.mission.domain.test.exception.code;

import com.umc.mission.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TestErrorCode implements BaseErrorCode {
    TEST_EXCEPTION(
            HttpStatus.BAD_REQUEST,
            "TEST400_1",
            "이것은 테스트입니다!"
    ),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
