package com.example.project_6.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class QuickValidation {
    public static <T> List<String> validate(T object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var violations = validator.validate(object);
        
        List<String> errors = new ArrayList<>();
        violations.forEach((v) -> {
            errors.add(v.getMessage());
        });

        return errors;
    }
}
