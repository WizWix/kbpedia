package io.github.wizwix.knowledgegarden.dto.response;

import java.util.Set;

public record ResponseAuth(
    String username,
    Set<String> roles
) {
}
