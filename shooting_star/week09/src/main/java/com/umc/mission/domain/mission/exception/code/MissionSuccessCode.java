package com.umc.mission.domain.mission.exception.code;

import com.umc.mission.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    FOUND(
         HttpStatus.FOUND,
         "MISSION200_1",
         "리뷰 조회가 완료되었습니다."
    ),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
