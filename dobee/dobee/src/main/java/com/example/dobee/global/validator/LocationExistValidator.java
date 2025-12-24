package com.example.dobee.global.validator;

import com.example.dobee.domain.store.repository.LocationRepository;
import com.example.dobee.global.annotation.ExistLocation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationExistValidator implements ConstraintValidator<ExistLocation, Long> {

    private final LocationRepository locationRepository;

    @Override
    public void initialize(ExistLocation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return locationRepository.existsById(value);
    }
}
