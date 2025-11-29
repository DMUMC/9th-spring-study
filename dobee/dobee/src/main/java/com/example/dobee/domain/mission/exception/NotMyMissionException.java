package com.example.dobee.domain.mission.exception;

import com.example.dobee.global.exception.CustomException;
import com.example.dobee.global.code.CommonResponseCode;

public class NotMyMissionException extends CustomException {
    public NotMyMissionException(String message) {
        super(CommonResponseCode.NOT_MY_MISSION, message);
    }
}
