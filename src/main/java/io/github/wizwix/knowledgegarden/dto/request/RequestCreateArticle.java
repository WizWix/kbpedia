package io.github.wizwix.knowledgegarden.dto.request;

import java.util.Set;

public record RequestCreateArticle(
    String title,
    String content,
    Set<String> tags
) {
}
