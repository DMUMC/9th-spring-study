package com.example.dobee.domain.store.exception.code;

import com.example.dobee.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    // Store Error Code
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_001", "존재하지 않는 가게입니다."),
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_002", "존재하지 않는 지역입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
