package com.umc.mission.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.PARAMETER) // 메서드의 파라미터에만 붙일 수 있다는 조건
@Retention(RetentionPolicy.RUNTIME) // 런타임에도 해당 어노테이션의 정보를 유지함을 알림
public @interface CheckPage {
}
