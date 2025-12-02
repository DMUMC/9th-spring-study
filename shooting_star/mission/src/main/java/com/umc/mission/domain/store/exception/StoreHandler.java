package com.umc.mission.domain.store.exception;

import com.umc.mission.global.apiPayload.code.BaseErrorCode;
import com.umc.mission.global.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode code) {
        super(code);
    }
}
