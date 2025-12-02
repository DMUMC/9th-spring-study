package com.umc.mission.domain.auth.enums;

import com.umc.mission.global.code.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ResponseCode {

    DUPLICATE_EMAIL("AT001", "Email already exists"),
    INVALID_CREDENTIALS("AT002", "Invalid email or password"),
    USER_NOT_FOUND("AT003", "User not found"),
    INVALID_TOKEN("AT004", "Invalid token"),
    EXPIRED_TOKEN("AT005", "Expired token"),
    UNAUTHORIZED("AT006", "Authentication required");

    private final String statusCode;
    private final String message;
}