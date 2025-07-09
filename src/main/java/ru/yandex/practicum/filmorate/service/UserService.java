package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserStorage userStorage;

    public User persist(User user) {
        return userStorage.persist(user);
    }

    public User update(User user) {
        return userStorage.update(user)
                .orElseThrow(() -> new NotFoundException("Пользователь с id=" + user.getId() + " не найден"));
    }

    public Collection<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public User getUser(Long id) {
        return userStorage.getUser(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с id=" + id + " не найден"));
    }

    public Collection<User> getFriends(Long id) {
        return userStorage.getFriends(id);
    }

    public void addFriend(Long userId, Long otherUserId) {
        User user = userStorage.getUser(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id=" + userId + " не найден"));
        User otherUser = userStorage.getUser(otherUserId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id=" + otherUserId + " не найден"));

        userStorage.addFriend(userId, otherUserId);
    }

    public void removeFriend(Long userId, Long otherUserId) {
        User user = userStorage.getUser(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id=" + userId + " не найден"));
        User otherUser = userStorage.getUser(otherUserId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id=" + otherUserId + " не найден"));

        userStorage.removeFriend(userId, otherUserId);
    }

    public List<User> getCommonFriends(Long userId, Long otherUserId) {
        Set<Long> commonIds = userStorage.getCommonFriends(userId, otherUserId);
        return commonIds.stream()
                .map(id -> userStorage.getUser(id)
                        .orElseThrow(() -> new NotFoundException("Пользователь с id=" + id + " не найден")))
                .collect(Collectors.toList());
    }
}
