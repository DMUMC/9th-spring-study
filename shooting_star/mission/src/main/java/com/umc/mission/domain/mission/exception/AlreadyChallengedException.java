package com.umc.mission.domain.mission.exception;

import com.umc.mission.domain.mission.enums.MissionErrorCode;
import com.umc.mission.global.exception.CustomException;

public class AlreadyChallengedException extends CustomException {
    public AlreadyChallengedException() {
        super(MissionErrorCode.ALREADY_CHALLENGED);
    }

    public AlreadyChallengedException(String message) {
        super(MissionErrorCode.ALREADY_CHALLENGED, message);
    }
}