package com.example.validation.constraints;

import com.example.validation.constraints.annotations.BlackList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class BlackListValidator
        implements ConstraintValidator<BlackList, String> {

    private Set<String> blacklist;

    @Override
    public void initialize(BlackList constraintAnnotation) {
        blacklist = new HashSet<>();
        for (String target: constraintAnnotation.blacklist()) {
            blacklist.add(target);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !this.blacklist.contains(value);
    }
}
