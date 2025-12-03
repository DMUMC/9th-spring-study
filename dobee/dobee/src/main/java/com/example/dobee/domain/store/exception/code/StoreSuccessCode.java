package com.example.dobee.domain.store.exception.code;

import com.example.dobee.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    // Store Success Code
    STORE_ADD_SUCCESS(HttpStatus.CREATED, "STORE_001", "가게 추가가 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
