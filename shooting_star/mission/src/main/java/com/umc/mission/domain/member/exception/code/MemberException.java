package com.umc.mission.domain.member.exception.code;

import com.umc.mission.global.apiPayload.code.BaseErrorCode;
import com.umc.mission.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
