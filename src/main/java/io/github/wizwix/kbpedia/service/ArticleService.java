package io.github.wizwix.kbpedia.service;

import io.github.wizwix.kbpedia.dto.Article;
import io.github.wizwix.kbpedia.repo.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService implements IArticleService {
  private final IArticleRepository repo;

  @Override
  public Page<Article> getArticles(String username, Pageable pageable) {
    if (username == null || username.isBlank()) {
      return repo.findAll(pageable);
    }
    return repo.findByAuthorUsername(username, pageable);
  }
}
