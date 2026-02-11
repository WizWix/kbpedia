package io.github.wizwix.kbpedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KbpediaApplication {
  public static void main(String[] args) {
    SpringApplication.run(KbpediaApplication.class, args);
  }
}
