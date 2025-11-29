package com.example.dobee.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PageErrorCode implements ResponseCode {
    INVALID_PAGE(HttpStatus.BAD_REQUEST, "PAGE_001", "Wrong Page Number."),
    ;

    private final HttpStatus httpStatus;
    private final String statusCode;
    private final String message;
}
