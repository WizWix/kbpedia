package io.github.wizwix.knowledgegarden.service.iface;

import io.github.wizwix.knowledgegarden.dto.response.ResponseArticleDetail;
import io.github.wizwix.knowledgegarden.dto.response.ResponseArticleList;
import io.github.wizwix.knowledgegarden.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IArticleService {
  ResponseArticleDetail createArticle(String title, String content, Set<String> tagNames, User author);

  ResponseArticleDetail getArticle(Long id);

  Page<ResponseArticleList> getArticles(String username, Pageable pageable);

  Page<ResponseArticleList> searchArticles(String keyword, Pageable pageable);
}
