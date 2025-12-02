package com.umc.mission.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PageErrorCode implements BaseErrorCode {
    BAD_REQUEST(
            HttpStatus.BAD_REQUEST,
            "PAGE404_1",
            "페이지 범위에 벗어난 값을 보냈습니다.(누락되었거나 1미만인 값)"
    ),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
