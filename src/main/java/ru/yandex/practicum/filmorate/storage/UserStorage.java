package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserStorage {

    User persist(User user);

    Optional<User> update(User user);

    Collection<User> getAllUsers();

    Optional<User> getUser(Long id);

    Collection<User> getFriends(Long id);

    void addFriend(Long userId, Long otherUserId);

    void removeFriend(Long userId, Long otherUserId);

    Set<Long> getCommonFriends(Long userId, Long otherUserId);
}
