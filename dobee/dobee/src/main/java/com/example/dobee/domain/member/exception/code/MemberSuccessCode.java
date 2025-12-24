package com.example.dobee.domain.member.exception.code;

import com.example.dobee.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    // Member Success Code
    MEMBER_JOIN_SUCCESS(HttpStatus.CREATED, "MEMBER_001", "회원가입이 완료되었습니다."),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK, "MEMBER_002", "로그인 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
