package com.example.matchapi.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SportValidator.class)
@Documented
public @interface ValidSport {
    String message() default "Invalid sport value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}