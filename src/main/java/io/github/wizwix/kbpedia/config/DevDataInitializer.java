package io.github.wizwix.kbpedia.config;

import io.github.wizwix.kbpedia.dto.User;
import io.github.wizwix.kbpedia.helper.Role;
import io.github.wizwix.kbpedia.repo.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DevDataInitializer {
  private final PasswordEncoder passwordEncoder;
  private final IUserRepository userRepository;

  @Bean
  @Profile("dev")
  public CommandLineRunner initDevData() {
    return args -> {
      if (userRepository.findByUsername("admin1").isEmpty()) {
        User admin = new User();
        admin.setUsername("admin1");
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setEmail("admin@email.com");
        admin.setRoles(Set.of(Role.ROLE_ADMIN, Role.ROLE_USER));
        userRepository.save(admin);
        log.info("Dev Profile: Admin account created (admin1/1234)");
      }
      if (userRepository.findByUsername("user1").isEmpty()) {
        User user = new User();
        user.setUsername("user1");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setEmail("user@email.com");
        user.setRoles(Set.of(Role.ROLE_USER));
        userRepository.save(user);
        log.info("Dev Profile: User account created (user1/1234)");
      }
    };
  }

  @Bean
  @Profile("!dev")
  public CommandLineRunner removeDevData() {
    return args -> {
      List<String> devUserNames = List.of("admin1", "user1");
      for (var devUserName : devUserNames) {
        userRepository.findByUsername(devUserName).ifPresent(user -> {
          boolean isDevEmail = "admin@email.com".equals(user.getEmail()) || "user@email.com".equals(user.getEmail());
          boolean isDevPassword = passwordEncoder.matches("1234", user.getPassword());
          if (isDevEmail && isDevPassword) {
            userRepository.delete(user);
          }
        });
      }
    };
  }
}
