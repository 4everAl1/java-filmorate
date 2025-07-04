package ru.yandex.practicum.filmorate.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotBeforeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBefore {
    String value();

    String message() default "Дата должна быть не раньше {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
