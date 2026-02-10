package io.github.wizwix.kbpedia.controller;

import io.github.wizwix.kbpedia.dto.User;
import io.github.wizwix.kbpedia.jwt.JwtUtils;
import io.github.wizwix.kbpedia.repo.IUserRepository;
import io.github.wizwix.kbpedia.request.AuthResponse;
import io.github.wizwix.kbpedia.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final JwtUtils jwtUtils;
  private final PasswordEncoder passwordEncoder;
  private final IUserRepository userRepository;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest req) {
    Optional<User> userOpt = userRepository.findByUsername(req.getUsername());
    if (userOpt.isEmpty() || !passwordEncoder.matches(req.getPassword(), userOpt.get().getPassword())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "사용자 이름 또는 비밀번호가 잘못되었습니다."));
    }
    User user = userOpt.get();
    String token = jwtUtils.generateToken(user.getUsername(), user.getRoles());
    return ResponseEntity.ok(new AuthResponse(token, user.getUsername(), user.getRoles()));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout() {
    return ResponseEntity.ok("Logged out successfully");
  }
}
