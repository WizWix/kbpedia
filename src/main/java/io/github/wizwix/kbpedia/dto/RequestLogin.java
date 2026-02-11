package io.github.wizwix.kbpedia.dto;

import lombok.Data;

@Data
public class RequestLogin {
  private String username;
  private String password;
}
