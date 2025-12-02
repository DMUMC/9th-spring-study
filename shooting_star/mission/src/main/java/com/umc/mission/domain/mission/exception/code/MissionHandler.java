package com.umc.mission.domain.mission.exception.code;

import com.umc.mission.global.apiPayload.code.BaseErrorCode;
import com.umc.mission.global.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode code) {
        super(code);
    }
}
