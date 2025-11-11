package com.example.dobee.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode implements ResponseCode {

    //성공
   OK("S000", "success"),
   CREATED("S001", "created success");

    private final String statusCode;
    private final String message;
}
