package io.github.wizwix.kbpedia.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthResponse {
  private String token;
  private String username;
  private List<String> roles;
}
