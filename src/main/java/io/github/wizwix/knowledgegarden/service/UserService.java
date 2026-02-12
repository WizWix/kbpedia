package io.github.wizwix.knowledgegarden.service;

import io.github.wizwix.knowledgegarden.model.User;
import io.github.wizwix.knowledgegarden.repo.IUserRepository;
import io.github.wizwix.knowledgegarden.service.iface.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements IUserService {
  private final PasswordEncoder encoder;
  private final IUserRepository repo;

  @Override
  @Transactional
  public User authenticate(String username, String rawPassword) {
    // user not found
    User user = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("Invalid username or password"));
    // password incorrect
    if (!encoder.matches(rawPassword, user.getPassword())) {
      throw new RuntimeException("Invalid username or password");
    }
    return user;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return repo.findByUsername(username);
  }
}
