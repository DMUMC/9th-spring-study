package com.umc.mission.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.umc.mission.global.apiPayload.code.BaseErrorCode;
import com.umc.mission.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// java 객체를 json으로 변환(직렬화)할 때, 필드 순서를 명시된 순서대로 강제함
public class ApiResponse<T> {
    // 필드를 json으로 만들 때, json의 key를 사용함
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    private final String code;

    private final String message;
    // api 요청의 실제 결과 데이터가 담기는 필드
    // 성공 시 dto가, 실패 시 null이나 빈 객체가 담길 수 있음
    @JsonProperty("result")
    private T result;

    // 실패한 경우
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }

    // 성공한 경우
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result){
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }
}
