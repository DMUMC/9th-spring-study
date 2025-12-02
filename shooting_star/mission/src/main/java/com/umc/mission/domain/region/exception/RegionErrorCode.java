package com.umc.mission.domain.region.exception;

import com.umc.mission.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RegionErrorCode implements BaseErrorCode {
    NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "REGION404_1",
            "지역을 찾을 수 없습니다."
    ),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
