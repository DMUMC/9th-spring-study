package com.example.dobee.domain.mission.exception;


import com.example.dobee.domain.mission.enums.MissionErrorCode;
import com.example.dobee.global.exception.CustomException;

public class MissionNotFoundException extends CustomException {
    public MissionNotFoundException() {
        super(MissionErrorCode.MISSION_NOT_FOUND);
    }

    public MissionNotFoundException(String message) {
        super(MissionErrorCode.MISSION_NOT_FOUND, message);
    }
}
