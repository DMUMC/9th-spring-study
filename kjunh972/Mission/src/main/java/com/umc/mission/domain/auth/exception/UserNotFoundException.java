package com.umc.mission.domain.auth.exception;

import com.umc.mission.domain.auth.enums.AuthErrorCode;
import com.umc.mission.global.exception.CustomException;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(AuthErrorCode.USER_NOT_FOUND);
    }

    public UserNotFoundException(String message) {
        super(AuthErrorCode.USER_NOT_FOUND, message);
    }
}