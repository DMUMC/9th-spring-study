package com.example.dobee.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode{
    OK(HttpStatus.OK,
            "200",
            "OK"),
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
