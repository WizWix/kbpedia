package io.github.wizwix.kbpedia.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ResponseAuth {
  private String username;
  private Set<String> roles;
}
