package com.umc.mission.domain.auth.exception;

import com.umc.mission.domain.auth.enums.AuthErrorCode;
import com.umc.mission.global.exception.CustomException;

public class DuplicateEmailException extends CustomException {
    public DuplicateEmailException() {
        super(AuthErrorCode.DUPLICATE_EMAIL);
    }

    public DuplicateEmailException(String message) {
        super(AuthErrorCode.DUPLICATE_EMAIL, message);
    }
}