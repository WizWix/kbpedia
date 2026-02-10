package io.github.wizwix.kbpedia.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {
  private final long expiration;
  private final SecretKey key;

  public JwtUtils(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    this.expiration = expiration;
  }

  public String generateToken(String username, List<String> roles) {
    return Jwts.builder().subject(username).claim("roles", roles).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expiration)).signWith(key).compact();
  }

  public Claims getClaims(String token) {
    return Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload();
  }
}
