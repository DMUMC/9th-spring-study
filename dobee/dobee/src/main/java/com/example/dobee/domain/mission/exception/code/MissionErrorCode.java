package com.example.dobee.domain.mission.exception.code;

import com.example.dobee.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    // Mission Error Code
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_001", "존재하지 않는 미션입니다."),
    ALREADY_CHALLENGING_MISSION(HttpStatus.BAD_REQUEST, "MISSION_002", "이미 도전 중인 미션입니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_003", "존재하지 않는 도전 정보입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
