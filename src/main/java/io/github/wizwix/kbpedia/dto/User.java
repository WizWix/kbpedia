package io.github.wizwix.kbpedia.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true, nullable = false)
  private String username;
  /// Encrypted password hash
  @Column(nullable = false)
  private String password;
  @Column(unique = true, nullable = false)
  private String email;
  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> roles; // elements are either 'ROLE_ADMIN' or 'ROLE_USER' or both

}
/*
  Both 'username' and 'email' should be UNIQUE.
  Let's say that [username: 1, email: 1] row exists. No other rows can have either [username: 1] or [email: 1].
  How should I declare this in JPA?
 */
