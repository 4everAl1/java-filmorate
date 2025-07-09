package ru.yandex.practicum.filmorate.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    @JsonProperty("friends")
    private final Set<Long> friendsSetId = new HashSet<>();
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

    public String getName() {
        return name == null ? login : name;
    }
}
