package com.example.dobee.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode{

    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "400_1",
            "Wrong Request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "401_1",
            "You Need Authorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "403_1",
            "Access Denied"),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "404_1",
            "Not Found Resource"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "500_1",
            "Internal Server Error")
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
