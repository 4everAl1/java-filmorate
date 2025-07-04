package ru.yandex.practicum.filmorate.model;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private Long id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "\\S+")
    private String login;
    private String name;
    @PastOrPresent
    private LocalDate birthday;

}
