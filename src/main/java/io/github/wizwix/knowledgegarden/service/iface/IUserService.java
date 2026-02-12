package io.github.wizwix.knowledgegarden.service.iface;

import io.github.wizwix.knowledgegarden.dto.User;

import java.util.Optional;

public interface IUserService {
  User authenticate(String username, String rawPassword);

  Optional<User> findByUsername(String username);
}
