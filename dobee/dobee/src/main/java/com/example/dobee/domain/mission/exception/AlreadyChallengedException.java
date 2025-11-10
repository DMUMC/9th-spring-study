package com.example.dobee.domain.mission.exception;


import com.example.dobee.domain.mission.enums.MissionErrorCode;
import com.example.dobee.global.exception.CustomException;

public class AlreadyChallengedException extends CustomException {
    public AlreadyChallengedException() {
        super(MissionErrorCode.ALREADY_CHALLENGED);
    }

    public AlreadyChallengedException(String message) {
        super(MissionErrorCode.ALREADY_CHALLENGED, message);
    }
}
