package io.github.wizwix.knowledgegarden.controller;

import io.github.wizwix.knowledgegarden.dto.message.ResponseArticleDetail;
import io.github.wizwix.knowledgegarden.dto.message.ResponseArticleList;
import io.github.wizwix.knowledgegarden.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ControllerRestArticle {
  private final ArticleService service;

  @GetMapping("/{id}")
  public ResponseEntity<ResponseArticleDetail> getArticle(@PathVariable Long id) {
    return ResponseEntity.ok(service.getArticle(id));
  }

  /// Get KB articles written by certain user
  @GetMapping
  public ResponseEntity<Page<ResponseArticleList>> getArticles(
      @RequestParam(value = "username", required = false) String username,
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "page", defaultValue = "1") int page
  ) {
    Pageable pageable = PageRequest.of(Math.max(page - 1, 0), 30);
    // if a keyword is present, use search logic. otherwise, use standard list/username filter.
    if (keyword != null && !keyword.isBlank()) {
      return ResponseEntity.ok(service.searchArticles(keyword, pageable));
    }

    return ResponseEntity.ok(service.getArticles(username, pageable));
  }
}
