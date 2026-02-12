package io.github.wizwix.knowledgegarden.dto.response;

import java.time.LocalDateTime;

public record ResponseArticleList(
    Long id,
    String title,
    String authorUsername,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
