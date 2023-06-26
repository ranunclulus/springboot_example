package com.example.validation.constraints;

import com.example.validation.constraints.annotations.Phone010;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Phone010Validator
        implements ConstraintValidator<Phone010, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.startsWith("010") || value.startsWith("010-");
    }
}
