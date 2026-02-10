package io.github.wizwix.kbpedia.config;

import io.github.wizwix.kbpedia.jwt.JwtAuthenticationFilter;
import io.github.wizwix.kbpedia.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final JwtUtils jwtUtils;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            // static resource
            .requestMatchers("/", "/index.html", "/static/**", "/assets/**", "/favicon.ico").permitAll()
            .requestMatchers("/error").permitAll()
            // public frontend route
            .requestMatchers("/login", "/register", "/search/**", "/user/**").permitAll()
            .requestMatchers("/{category}/**").permitAll()
            // public api endpoint
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/articles/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
            // authenticated api & frontend route
            .requestMatchers("/my/**", "/new", "/**/edit").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
            // admin only
            .requestMatchers("/manage/**", "/api/admin/**").hasRole("ADMIN")
            // other requests should be authenticated
            .anyRequest().authenticated()
        )
        .addFilterBefore(new JwtAuthenticationFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
