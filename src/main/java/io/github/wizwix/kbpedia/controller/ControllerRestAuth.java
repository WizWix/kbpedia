package io.github.wizwix.kbpedia.controller;

import io.github.wizwix.kbpedia.dto.User;
import io.github.wizwix.kbpedia.dto.message.RequestLogin;
import io.github.wizwix.kbpedia.jwt.JwtUtils;
import io.github.wizwix.kbpedia.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ControllerRestAuth {
  private final JwtUtils jwtUtils;
  private final UserService service;

  @GetMapping("/me")
  public ResponseEntity<?> getCurrentUser(Authentication auth) {
    if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    return ResponseEntity.ok(Map.of(
        "username", auth.getName(),
        "roles", auth.getAuthorities()
    ));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody RequestLogin req) {
    User user = service.authenticate(req.getUsername(), req.getPassword());
    String token = jwtUtils.generateToken(user.getUsername(), user.getRoles().stream().map(Enum::name).toList());
    ResponseCookie cookie = ResponseCookie.from("jwt_token", token)
        .httpOnly(true)
        .secure(false) // TODO: set this to `true` in HTTPS production!
        .path("/")
        .maxAge(jwtUtils.getExpiration() / 1000)
        .sameSite("Strict")
        .build();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(Map.of("username", user.getUsername(), "roles", user.getRoles()));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletResponse resp) {
    ResponseCookie cookie = ResponseCookie.from("jwt_token", "")
        .httpOnly(true)
        .path("/")
        .maxAge(0)
        .build();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("Logged out");
  }
}
