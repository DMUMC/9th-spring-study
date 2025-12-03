package com.example.dobee.domain.member.exception.code;

import com.example.dobee.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {
    // Food Error Code
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_001", "존재하지 않는 음식 종류입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
