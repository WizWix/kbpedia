package io.github.wizwix.kbpedia.service;

import io.github.wizwix.kbpedia.dto.User;

import java.util.Optional;

public interface IUserService {
  User authenticate(String username, String rawPassword);

  Optional<User> findByUsername(String username);
}
