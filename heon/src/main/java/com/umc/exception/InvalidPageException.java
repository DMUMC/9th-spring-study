package com.umc.exception;

import com.umc.apiController.code.BaseResponseCode;
import lombok.Getter;

@Getter
public class InvalidPageException extends RuntimeException {
    
    private final BaseResponseCode errorCode;
    
    public InvalidPageException(BaseResponseCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    
    public InvalidPageException(BaseResponseCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
