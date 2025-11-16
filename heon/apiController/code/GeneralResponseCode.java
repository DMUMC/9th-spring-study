package com.umc.apiController.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.gradle.internal.impldep.org.apache.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralResponseCode implements BaseResponseCode {
    BAD_REQUEST(HttpStatus.SC_BAD_REQUEST, "400_1", "잘못된 요청"),
    UNAUTHORIZED(HttpStatus.SC_UNAUTHORIZED,"401_1", "인증 실패"),
    FORBIDDEN(HttpStatus.SC_FORBIDDEN, "403_1", "요청 거부됨"),
    NOT_FOUND(HttpStatus.SC_NOT_FOUND, "404_1", "요청 대상 찾지 못함");

    private final int httpStatus;
    private final String code;
    private final String message;
}
