package com.umc.mission.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.umc.mission.global.code.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 모든 응답이 {"status": {...}, "body": {...}} 구조를 갖도록 통일
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private ResponseStatus status;
    private T body;

    /**
     * DELETE, POST 요청 등 별도 데이터 없이 성공 상태만 반환
     */
    public static <T> ApiResponse<T> ok() {
        return ApiResponse.ok(null);
    }

    /**
     * GET 요청 등 데이터와 함께 성공 상태 반환
     */
    public static <T> ApiResponse<T> ok(T body) {
        var apiResponse = new ApiResponse<T>();
        apiResponse.status = ResponseStatus.ok();
        apiResponse.body = body;
        return apiResponse;
    }

    public static <T> ApiResponse<T> error() {
        var apiResponse = new ApiResponse<T>();
        apiResponse.status = ResponseStatus.error();
        return apiResponse;
    }

    public static <T> ApiResponse<T> error(ResponseCode responseCode) {
        var apiResponse = new ApiResponse<T>();
        apiResponse.status = ResponseStatus.error(responseCode);
        return apiResponse;
    }

    public static <T> ApiResponse<T> error(ResponseCode responseCode, String description) {
        var apiResponse = new ApiResponse<T>();
        apiResponse.status = ResponseStatus.error(responseCode, description);
        return apiResponse;
    }
} 