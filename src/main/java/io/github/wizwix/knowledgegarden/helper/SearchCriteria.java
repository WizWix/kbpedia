package io.github.wizwix.knowledgegarden.helper;

import java.util.Set;

public record SearchCriteria(Set<String> include, Set<String> exclude) {
}
