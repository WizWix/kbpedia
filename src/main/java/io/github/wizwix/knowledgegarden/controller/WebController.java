package io.github.wizwix.knowledgegarden.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
  @SuppressWarnings("MVCPathVariableInspection")
  @GetMapping(value = {"/", "/{path:[^.]*}", "/**/{path:[^.]*}"})
  public String redirect() {
    return "forward:/index.html";
  }
}
