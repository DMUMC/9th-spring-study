package com.example.dobee.global.response;

import com.example.dobee.global.code.ResponseCode;
import com.example.dobee.global.code.SuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private ResponseStatus status;
    private T body;

    public static <T> ApiResponse<T> onSuccess(SuccessCode successCode) {
        var apiResponse = new ApiResponse<T>();
        apiResponse.status = ResponseStatus.onSuccess(successCode);
        return apiResponse;
    }

    public static <T> ApiResponse<T> onSuccess(SuccessCode successCode,T body) {
        var apiResponse = new ApiResponse<T>();
        apiResponse.status = ResponseStatus.onSuccess(successCode);
        apiResponse.body = body;
        return apiResponse;
    }

    public static <T> ApiResponse<T> onFailure(ResponseCode responseCode) {
        var apiResponse = new ApiResponse<T>();
        apiResponse.status = ResponseStatus.onFailure(responseCode);
        return apiResponse;
    }

    public static <T> ApiResponse<T> onFailure(ResponseCode responseCode, String description) {
        var apiResponse = new ApiResponse<T>();
        apiResponse.status = ResponseStatus.onFailure(responseCode, description);
        return apiResponse;
    }
}
