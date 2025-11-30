package com.umc.apiController.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralResponseCode implements BaseResponseCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400_1", "잘못된 요청"),
    INVALID_PAGE(HttpStatus.BAD_REQUEST, "400_2", "페이지 번호는 1 이상이어야 합니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"401_1", "인증 실패"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "403_1", "요청 거부됨"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "404_1", "요청 대상 찾지 못함"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "404_2", "회원을 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "404_3", "가게를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
