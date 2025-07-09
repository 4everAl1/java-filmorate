package ru.yandex.practicum.filmorate.storage.inmemorystorage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> films = new HashMap<>();
    private long id = 0;

    @Override
    public Collection<Film> getAllFilms() {
        return films.values();
    }

    @Override
    public Film persist(Film film) {
        film.setId(generateId());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Optional<Film> update(Film film) {
        return Optional.ofNullable(films.computeIfPresent(film.getId(), (k, v) -> film));
    }

    @Override
    public Optional<Film> getFilm(Long id) {
        return Optional.ofNullable(films.get(id));
    }

    @Override
    public Collection<Film> getTopRateFilms(Long count) {
        return films.values().stream()
                .sorted((f1, f2) -> f2.getUserLikes().size() - f1.getUserLikes().size())
                .limit(Math.max(0, count))
                .toList();
    }

    @Override
    public void addLike(Long filmId, Long userId) {
        if (films.containsKey(filmId)) {
            films.get(filmId).getUserLikes().add(userId);
        }
    }

    @Override
    public void removeLike(Long filmId, Long userId) {
        if (films.containsKey(filmId)) {
            films.get(filmId).getUserLikes().remove(userId);
        }
    }

    private long generateId() {
        return ++id;
    }
}
