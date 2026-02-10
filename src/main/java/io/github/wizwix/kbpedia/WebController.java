package io.github.wizwix.kbpedia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
  @GetMapping(value = "{path:(?!api|static)[^\\.]*}")
  public String redirect() {
    return "forward:/index.html";
  }
}
