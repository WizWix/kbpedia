package io.github.wizwix.kbpedia.request;

import lombok.Data;

@Data
public class LoginRequest {
  private String username;
  private String password;
}
