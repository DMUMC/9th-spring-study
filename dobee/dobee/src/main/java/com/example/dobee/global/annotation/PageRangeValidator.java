package com.example.dobee.global.annotation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageRangeValidator implements ConstraintValidator<PageRange, Integer> {

    @Override
    public void initialize(PageRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        return page > 0;
    }
}
