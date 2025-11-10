package com.example.matchapi.dto;
import com.example.matchapi.model.Sport;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SportValidator implements ConstraintValidator<ValidSport, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        try {
            Sport.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}