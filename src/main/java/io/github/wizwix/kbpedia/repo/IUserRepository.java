package io.github.wizwix.kbpedia.repo;

import io.github.wizwix.kbpedia.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
