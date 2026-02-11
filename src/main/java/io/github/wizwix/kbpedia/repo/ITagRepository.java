package io.github.wizwix.kbpedia.repo;

import io.github.wizwix.kbpedia.dto.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITagRepository extends JpaRepository<Tag, Long> {
  Optional<Tag> findByName(String name);
}
