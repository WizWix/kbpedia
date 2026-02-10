package io.github.wizwix.kbpedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
  @GetMapping(value = "{path:(?!api|static|assets|favicon.ico|error)[^.]*}")
  public String redirect() {
    return "forward:/index.html";
  }
}
