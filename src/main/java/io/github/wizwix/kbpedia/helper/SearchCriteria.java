package io.github.wizwix.kbpedia.helper;

import java.util.Set;

public record SearchCriteria(Set<String> include, Set<String> exclude) {
}
