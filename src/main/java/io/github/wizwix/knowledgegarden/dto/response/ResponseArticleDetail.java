package io.github.wizwix.knowledgegarden.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public record ResponseArticleDetail(
    Long id,
    String title,
    String content,
    Set<String> tags,
    String authorUsername,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
