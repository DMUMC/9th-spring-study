package com.umc.mission.domain.auth.exception;

import com.umc.mission.domain.auth.enums.AuthErrorCode;
import com.umc.mission.global.exception.CustomException;

public class InvalidCredentialsException extends CustomException {
    public InvalidCredentialsException() {
        super(AuthErrorCode.INVALID_CREDENTIALS);
    }

    public InvalidCredentialsException(String message) {
        super(AuthErrorCode.INVALID_CREDENTIALS, message);
    }
}