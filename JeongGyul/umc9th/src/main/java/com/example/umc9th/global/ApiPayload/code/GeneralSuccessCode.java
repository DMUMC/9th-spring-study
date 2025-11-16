package com.example.umc9th.global.ApiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "COMMON200",
            "성공적으로 요청을 처리했습니다."),
    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "요청 성공 및 리소스가 생성되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}