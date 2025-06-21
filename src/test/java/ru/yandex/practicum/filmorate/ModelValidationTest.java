package ru.yandex.practicum.filmorate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ModelValidationTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    // ==== Тесты для Film ====

    @Test
    void filmWithBlankNameShouldHaveViolation() {
        Film film = new Film();
        film.setName("");
        film.setDescription("desc");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(Duration.ofMinutes(90));

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void filmWithTooLongDescriptionShouldHaveViolation() {
        Film film = new Film();
        film.setName("A");
        film.setDescription("x".repeat(201));
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(Duration.ofMinutes(90));

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("description")));
    }

    @Test
    void filmWithEarlyReleaseDateShouldHaveViolation() {
        Film film = new Film();
        film.setName("A");
        film.setDescription("desc");
        film.setReleaseDate(LocalDate.of(1800, 1, 1));
        film.setDuration(Duration.ofMinutes(90));

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("releaseDate")));
    }

    @Test
    void filmWithNegativeDurationShouldHaveViolation() {
        Film film = new Film();
        film.setName("A");
        film.setDescription("desc");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(Duration.ofSeconds(-10));

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("duration")));
    }

    @Test
    void filmValidShouldHaveNoViolations() {
        Film film = new Film();
        film.setName("Valid");
        film.setDescription("OK");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(Duration.ofMinutes(100));

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    // ==== Тесты для User ====

    @Test
    void userWithInvalidEmailShouldHaveViolation() {
        User user = new User();
        user.setEmail("ms.com");
        user.setLogin("login");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    void userWithLoginContainingSpaceShouldHaveViolation() {
        User user = new User();
        user.setEmail("a@gmail.com");
        user.setLogin("bad login");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("login")));
    }

    @Test
    void userWithFutureBirthdayShouldHaveViolation() {
        User user = new User();
        user.setEmail("a@gmail.com");
        user.setLogin("login");
        user.setBirthday(LocalDate.now().plusDays(1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("birthday")));
    }

    @Test
    void userValidShouldHaveNoViolations() {
        User user = new User();
        user.setEmail("a@gmail.com");
        user.setLogin("login");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }
}
