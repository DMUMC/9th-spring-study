package com.umc.mission.domain.mission.enums;

import com.umc.mission.global.code.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements ResponseCode {

    MISSION_NOT_FOUND("MS001", "Mission not found"),
    MEMBER_MISSION_NOT_FOUND("MS002", "Member mission not found"),
    ALREADY_CHALLENGED("MS003", "Already challenged this mission"),
    MISSION_CHALLENGE_FAILED("MS004", "Mission challenge failed"),
    REGION_NOT_FOUND("MS005", "Region not found"),
    NO_AVAILABLE_MISSIONS("MS006", "No available missions found"),
    MISSION_ALREADY_COMPLETED("MS007", "Mission is already completed"),
    MISSION_DEADLINE_EXPIRED("MS008", "Mission deadline has expired");

    private final String statusCode;
    private final String message;
}