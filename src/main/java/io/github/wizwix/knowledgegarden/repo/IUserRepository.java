package io.github.wizwix.knowledgegarden.repo;

import io.github.wizwix.knowledgegarden.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
