package io.github.wizwix.knowledgegarden.dto.message;

import lombok.Getter;

@Getter
public class RequestLogin {
  private String username;
  private String password;
}
