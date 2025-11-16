package com.umc.apiController.code;

import org.gradle.internal.impldep.org.apache.http.HttpStatus;

/**
 * 워크북에서는 성공 실패 인터페이스 나누던데 어차피 상태값 날아가는 양식은 같은데 굳이 나눌 이유를 모르겠어서 하나로 통일함
 */
public interface BaseResponseCode {
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}
