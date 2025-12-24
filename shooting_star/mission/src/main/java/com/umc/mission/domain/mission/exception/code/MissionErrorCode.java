package com.umc.mission.domain.mission.exception.code;

import com.umc.mission.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    ALREADY_CHALLENGE(
            HttpStatus.BAD_REQUEST,
            "MISSION401_1",
            "이미 도전중인 미션입니다."
    ),
    NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "요청한 미션 정보가 없습니다."
    ),
    ALREADY_COMPLETE(
            HttpStatus.BAD_REQUEST,
            "MISSION401_2",
            "이미 완료된 미션입니다."
    ),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
