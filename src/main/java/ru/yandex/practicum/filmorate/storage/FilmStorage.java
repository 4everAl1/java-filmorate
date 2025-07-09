package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmStorage {
    Film persist(Film film);

    Optional<Film> update(Film film);

    Collection<Film> getAllFilms();

    Optional<Film> getFilm(Long id);

    Collection<Film> getTopRateFilms(Long count);

    void addLike(Long filmId, Long userId);

    void removeLike(Long filmId, Long userId);
}
