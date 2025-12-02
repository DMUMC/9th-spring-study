package com.umc.mission.domain.mission.exception;

import com.umc.mission.domain.mission.enums.MissionErrorCode;
import com.umc.mission.global.exception.CustomException;

public class MissionNotFoundException extends CustomException {
    public MissionNotFoundException() {
        super(MissionErrorCode.MISSION_NOT_FOUND);
    }

    public MissionNotFoundException(String message) {
        super(MissionErrorCode.MISSION_NOT_FOUND, message);
    }
}