package com.example.dobee.domain.mission.exception.code;

import com.example.dobee.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    // Mission Success Code
    MISSION_ADD_SUCCESS(HttpStatus.CREATED, "MISSION_001", "미션 추가가 완료되었습니다."),
    MISSION_CHALLENGE_SUCCESS(HttpStatus.CREATED, "MISSION_002", "미션 도전이 시작되었습니다."),
    GET_MISSION_LIST_SUCCESS(HttpStatus.OK, "MISSION_003", "가게의 미션 목록 조회가 완료되었습니다."),
    GET_MY_CHALLENGING_MISSIONS_SUCCESS(HttpStatus.OK, "MISSION_004", "진행중인 미션 목록 조회가 완료되었습니다."),
    MISSION_COMPLETE_SUCCESS(HttpStatus.OK, "MISSION_005", "미션 완료 처리가 되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
