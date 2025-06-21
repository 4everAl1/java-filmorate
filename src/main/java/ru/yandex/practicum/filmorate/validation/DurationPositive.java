package ru.yandex.practicum.filmorate.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DurationPositiveValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DurationPositive {

    String message() default "Продолжительность не может быть отрицательной";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
