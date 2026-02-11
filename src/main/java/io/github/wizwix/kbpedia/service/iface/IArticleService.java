package io.github.wizwix.kbpedia.service.iface;

import io.github.wizwix.kbpedia.dto.Article;
import io.github.wizwix.kbpedia.dto.ResponseArticleDetail;
import io.github.wizwix.kbpedia.dto.ResponseArticleList;
import io.github.wizwix.kbpedia.dto.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IArticleService {
  Article createArticle(String title, String content, Set<String> tagNames, User author);

  ResponseArticleDetail getArticle(Long id);

  Page<ResponseArticleList> getArticles(String username, Pageable pageable);

  Page<ResponseArticleList> searchArticles(String keyword, Pageable pageable);
}
