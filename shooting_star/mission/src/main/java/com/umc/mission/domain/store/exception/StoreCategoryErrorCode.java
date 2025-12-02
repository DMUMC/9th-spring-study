package com.umc.mission.domain.store.exception;

import com.umc.mission.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreCategoryErrorCode implements BaseErrorCode {
    NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "STORE_CATEGORY404_1",
            "가게 카테고리를 찾을 수 없습니다."
    ),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
