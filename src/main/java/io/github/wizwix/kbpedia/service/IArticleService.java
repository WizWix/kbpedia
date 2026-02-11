package io.github.wizwix.kbpedia.service;

import io.github.wizwix.kbpedia.dto.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleService {
  Page<Article> getArticles(String username, Pageable pageable);
}
