package com.umc.mission.domain.region.exception;

import com.umc.mission.global.apiPayload.code.BaseErrorCode;
import com.umc.mission.global.apiPayload.exception.GeneralException;

public class RegionException extends GeneralException {
    public RegionException(BaseErrorCode code){
        super(code);
    }
}
