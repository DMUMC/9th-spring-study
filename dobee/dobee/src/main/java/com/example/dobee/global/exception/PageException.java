package com.example.dobee.global.exception;

import com.example.dobee.global.code.ResponseCode;

public class PageException extends CustomException {
    public PageException(ResponseCode responseCode) {
        super(responseCode);
    }
}
