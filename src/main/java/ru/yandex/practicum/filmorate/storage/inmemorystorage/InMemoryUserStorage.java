package ru.yandex.practicum.filmorate.storage.inmemorystorage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryUserStorage implements UserStorage {

    private final Map<Long, User> users = new HashMap<>();
    private long id = 0;

    @Override
    public User persist(User user) {
        user.setId(generateId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.ofNullable(users.computeIfPresent(user.getId(), (k, v) -> user));
    }

    @Override
    public Optional<User> getUser(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Set<User> getFriends(Long id) {
        User user = users.get(id);
        if (user == null) {
            throw new NotFoundException("Пользователь с id=" + id + " не найден");
        }

        return users.get(id).getFriendsSetId().stream()
                .map(users::get)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }

    @Override
    public void addFriend(Long userId, Long otherUserId) {

        User user = users.get(userId);
        User otherUser = users.get(otherUserId);

        if (user != null && otherUser != null) {
            user.getFriendsSetId().add(otherUserId);
            otherUser.getFriendsSetId().add(userId);
        }
    }

    @Override
    public void removeFriend(Long userId, Long otherUserId) {

        User user = users.get(userId);
        User otherUser = users.get(otherUserId);

        if (user != null && otherUser != null) {
            user.getFriendsSetId().remove(otherUserId);
            otherUser.getFriendsSetId().remove(userId);
        }
    }

    @Override
    public Set<Long> getCommonFriends(Long userId, Long otherUserId) {
        return users.get(userId).getFriendsSetId()
                .stream()
                .filter(users.get(otherUserId).getFriendsSetId()::contains)
                .collect(Collectors.toSet());
    }

    private long generateId() {
        return ++id;
    }
}
