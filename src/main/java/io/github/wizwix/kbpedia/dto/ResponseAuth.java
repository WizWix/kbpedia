package io.github.wizwix.kbpedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ResponseAuth {
  private String username;
  private Set<String> roles;
}
