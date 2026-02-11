package io.github.wizwix.kbpedia.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResponseArticleList {
  private Long id;
  private String title;
  private String authorUsername;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
