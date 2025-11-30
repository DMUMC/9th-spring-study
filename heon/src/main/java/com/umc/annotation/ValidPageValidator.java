package com.umc.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPageValidator implements ConstraintValidator<ValidPage, Integer> {
    
    @Override
    public void initialize(ValidPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        if (page == null) {
            return false;
        }
        return page >= 1;
    }
}
