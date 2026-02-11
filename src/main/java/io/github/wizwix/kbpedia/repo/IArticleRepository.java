package io.github.wizwix.kbpedia.repo;

import io.github.wizwix.kbpedia.dto.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<Article, Long> {
  @EntityGraph(attributePaths = {"author"})
  Page<Article> findByAuthorUsername(String username, Pageable pageable);

  Page<Article> findByAuthorId(Long authorId, Pageable pageable);
}
