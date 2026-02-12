package io.github.wizwix.knowledgegarden.dto;

import java.util.Set;

public record SearchCriteria(Set<String> include, Set<String> exclude) {
}
