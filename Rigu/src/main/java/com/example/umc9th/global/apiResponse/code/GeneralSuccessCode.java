package com.example.umc9th.global.apiResponse.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(
            HttpStatus.OK,
            "200",
            "성공적으로 요청을 처리했습니다."
    ),

    CREATED(
            HttpStatus.CREATED,
            "201",
            "요청 성공 및 리소스가 생성되었습니다."
    ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
