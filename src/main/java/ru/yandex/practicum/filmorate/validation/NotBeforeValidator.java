package ru.yandex.practicum.filmorate.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class NotBeforeValidator implements ConstraintValidator<NotBefore, LocalDate> {

    private LocalDate minDate;

    @Override
    public void initialize(NotBefore constraint) {
        try {
            this.minDate = LocalDate.parse(constraint.value());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Неверный формат даты " + constraint.value(), e);
        }
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext ctx) {
        if (value == null) {
            return false;
        }
        return !value.isBefore(minDate);
    }
}
