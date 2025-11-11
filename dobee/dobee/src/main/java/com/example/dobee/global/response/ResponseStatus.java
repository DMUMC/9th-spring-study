package com.example.dobee.global.response;

import com.example.dobee.global.code.ResponseCode;
import com.example.dobee.global.code.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {
    private String statusCode;
    private String message;
    private String description;

    public static ResponseStatus onSuccess(SuccessCode successCode) {
        return ResponseStatus.builder()
                .statusCode(successCode.getStatusCode())
                .message(successCode.getMessage())
                .build();
    }

    public static ResponseStatus onFailure(ResponseCode responseCode) {
        return ResponseStatus.builder()
                .statusCode(responseCode.getStatusCode())
                .message(responseCode.getMessage())
                .build();
    }

    public static ResponseStatus onFailure(ResponseCode responseCode, String description) {
        return ResponseStatus.builder()
                .statusCode(responseCode.getStatusCode())
                .message(responseCode.getMessage())
                .description(description)
                .build();
    }
}
