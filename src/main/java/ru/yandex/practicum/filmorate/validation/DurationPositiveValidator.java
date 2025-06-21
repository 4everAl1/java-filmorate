package ru.yandex.practicum.filmorate.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Duration;

public class DurationPositiveValidator implements ConstraintValidator<DurationPositive, Duration> {

    @Override
    public boolean isValid(Duration value, ConstraintValidatorContext ctx) {
        return value == null || value.toMinutes() > 0;
    }
}