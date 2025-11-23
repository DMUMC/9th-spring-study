package com.umc.apiController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.umc.apiController.code.BaseResponseCode;
import com.umc.apiController.code.GeneralSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T>{
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;


    // 사용 : return ApiResponse.onSuccess(data); return ApiResponse.onFailure(GeneralErrorCode.BAD_REQUEST, null);
    // CASE : 성공시 (기본 OK)
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, GeneralSuccessCode.OK.getCode(), GeneralSuccessCode.OK.getMessage(), result);
    }

    // CASE : 성공시 (특정 성공 코드 지정)
    public static <T> ApiResponse<T> onSuccess(BaseResponseCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    // CASE : 실패시
    public static <T> ApiResponse<T> onFailure(BaseResponseCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
