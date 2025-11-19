package com.example.dobee.domain.store.enums;

import com.example.dobee.global.code.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements ResponseCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "가게를 찾을 수 없습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "지역을 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;


    @Override
    public String getStatusCode() {
        return this.getStatusCode().toString();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
