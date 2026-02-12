package io.github.wizwix.knowledgegarden.service.iface;

import io.github.wizwix.knowledgegarden.dto.Article;
import io.github.wizwix.knowledgegarden.dto.User;
import io.github.wizwix.knowledgegarden.dto.message.ResponseArticleDetail;
import io.github.wizwix.knowledgegarden.dto.message.ResponseArticleList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IArticleService {
  Article createArticle(String title, String content, Set<String> tagNames, User author);

  ResponseArticleDetail getArticle(Long id);

  Page<ResponseArticleList> getArticles(String username, Pageable pageable);

  Page<ResponseArticleList> searchArticles(String keyword, Pageable pageable);
}
