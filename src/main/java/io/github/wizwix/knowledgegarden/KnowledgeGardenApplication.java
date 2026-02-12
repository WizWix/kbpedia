package io.github.wizwix.knowledgegarden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KnowledgeGardenApplication {
  public static void main(String[] args) {
    SpringApplication.run(KnowledgeGardenApplication.class, args);
  }
}
