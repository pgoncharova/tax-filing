package com.pgoncharova.taxfiling.user;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    void deleteUser(Long id);
}
