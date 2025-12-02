package com.umc.mission.domain.member.exception.code;

import com.umc.mission.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    FOUND(
            HttpStatus.OK,
            "MEMBER200_1",
            "사용자를 성공적으로 조회했습니다."
    ),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
