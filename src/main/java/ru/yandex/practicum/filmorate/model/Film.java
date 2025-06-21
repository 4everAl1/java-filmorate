package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.util.DurationDeserializer;
import ru.yandex.practicum.filmorate.util.DurationSerializer;
import ru.yandex.practicum.filmorate.validation.DurationPositive;
import ru.yandex.practicum.filmorate.validation.NotBefore;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Film.
 */
@Data
public class Film {

    private Long id;
    @NotBlank
    private String name;
    @Size(max = 200)
    private String description;
    @NotNull
    @NotBefore("1895-12-28")
    private LocalDate releaseDate;
    @DurationPositive
    @NotNull
    @JsonSerialize(using = DurationSerializer.class)
    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration duration;
}
