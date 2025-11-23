package com.umc.apiController.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseResponseCode {
    OK(HttpStatus.OK, "200_1", "요청 성공"),
    CREATED(HttpStatus.CREATED, "201_1", "생성 완료"),
    ACCEPTED(HttpStatus.ACCEPTED, "202_1", "요청 접수됨");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
