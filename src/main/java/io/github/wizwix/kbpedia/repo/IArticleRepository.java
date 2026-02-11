package io.github.wizwix.kbpedia.repo;

import io.github.wizwix.kbpedia.dto.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

public interface IArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
  Page<Article> findByAuthorId(Long authorId, Pageable pageable);

  @EntityGraph(attributePaths = {"author"})
  Page<Article> findByAuthorUsername(String username, Pageable pageable);

  Page<Article> findByTags_Name(String name, Pageable pageable);

  Page<Article> search(@Param("keyword") String keyword, Pageable pageable);
}
