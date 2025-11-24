package com.umc.mission.global.exception;

import com.umc.mission.global.code.CommonResponseCode;

public class InvalidPageException extends CustomException {
    public InvalidPageException() {
        super(CommonResponseCode.INVALID_PAGE_ERROR);
    }
}