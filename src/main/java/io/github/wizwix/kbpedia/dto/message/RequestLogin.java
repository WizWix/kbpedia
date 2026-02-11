package io.github.wizwix.kbpedia.dto.message;

import lombok.Data;

@Data
public class RequestLogin {
  private String username;
  private String password;
}
