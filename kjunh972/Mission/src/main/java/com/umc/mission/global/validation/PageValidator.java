package com.umc.mission.global.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {
    @Override
    public void initialize(ValidPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value >= 1;
    }
}