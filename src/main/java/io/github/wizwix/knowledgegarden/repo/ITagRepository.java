package io.github.wizwix.knowledgegarden.repo;

import io.github.wizwix.knowledgegarden.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITagRepository extends JpaRepository<Tag, Long> {
  Optional<Tag> findByName(String name);
}
