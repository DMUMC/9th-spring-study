package com.umc.mission.global.validation.validator;

import com.umc.mission.global.code.CommonResponseCode;
import com.umc.mission.global.exception.CustomException;
import com.umc.mission.global.validation.annotation.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null은 @NotNull 등 다른 어노테이션으로 처리
        }

        if (value < 1) {
            // 커스텀 예외를 던지거나 false를 반환하여 MethodArgumentNotValidException 유도
            // 여기서는 false를 반환하여 ConstraintViolationException이 발생하도록 함
            return false;
        }

        return true;
    }
}