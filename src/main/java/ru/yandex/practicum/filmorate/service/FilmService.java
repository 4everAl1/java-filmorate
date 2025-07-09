package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    public Collection<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Film persist(Film film) {
        return filmStorage.persist(film);
    }

    public Film getFilm(Long id) {
        return filmStorage.getFilm(id)
                .orElseThrow(() -> new NotFoundException("Фильм с id=" + id + " не найден"));
    }

    public Film update(Film film) {
        return filmStorage.update(film)
                .orElseThrow(() -> new NotFoundException("Фильм с id=" + film.getId() + " не найден"));
    }

    public void addLike(Long filmId, Long userId) {
        Film film = filmStorage.getFilm(filmId)
                .orElseThrow(() -> new NotFoundException("Фильм с id=" + filmId + " не найден"));
        User user = userStorage.getUser(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id=" + userId + " не найден"));

        filmStorage.addLike(filmId, userId);
    }

    public void removeLike(Long filmId, Long userId) {
        Film film = filmStorage.getFilm(filmId)
                .orElseThrow(() -> new NotFoundException("Фильм с id=" + filmId + " не найден"));
        User user = userStorage.getUser(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id=" + userId + " не найден"));

        filmStorage.removeLike(filmId, userId);
    }

    public Collection<Film> getTopRateFilms(Long count) {
        return filmStorage.getTopRateFilms(count);
    }
}
