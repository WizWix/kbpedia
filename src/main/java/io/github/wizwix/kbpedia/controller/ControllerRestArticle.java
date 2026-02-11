package io.github.wizwix.kbpedia.controller;

import io.github.wizwix.kbpedia.dto.Article;
import io.github.wizwix.kbpedia.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ControllerRestArticle {
  private final ArticleService service;

  @GetMapping
  public ResponseEntity<Page<Article>> getArticles(
      @RequestParam(value = "username", required = false) String username,
      @RequestParam(value = "page", defaultValue = "1") int page
  ) {
    int zeroIndexedPage = Math.max(page - 1, 0);
    Pageable pageable = PageRequest.of(zeroIndexedPage, 30);

    return ResponseEntity.ok(service.getArticles(username, pageable));
  }
}
