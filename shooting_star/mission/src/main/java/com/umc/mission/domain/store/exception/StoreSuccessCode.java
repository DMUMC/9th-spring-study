package com.umc.mission.domain.store.exception;

import com.umc.mission.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    OK(
            HttpStatus.OK,
            "STORE201_1",
            "가게가 성공적으로 추가되었습니다."
    ),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
