package com.umc.apiController.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.gradle.internal.impldep.org.apache.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseResponseCode {
    OK(HttpStatus.SC_OK, "200_1", "요청 성공"),
    CREATED(HttpStatus.SC_CREATED, "201_1", "생성 완료"),
    ACCEPTED(HttpStatus.SC_ACCEPTED, "202_1", "요청 접수됨");

    private final int httpStatus;
    private final String code;
    private final String message;
}