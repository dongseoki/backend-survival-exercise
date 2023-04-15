package com.example.securityexample.daos;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsDao {
  private final JdbcTemplate jdbcTemplate;

  public UserDetailsDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Optional<UserDetails> findByUsername(String username) {
    String query = "SELECT id, password, role FROM users WHERE username=?";

    return jdbcTemplate.query(query, resultSet -> {
      if (!resultSet.next()) {
        return Optional.empty();
      }

      String id = resultSet.getString("id");
      String password = resultSet.getString("password");
      String role = resultSet.getString("role");

      UserDetails userDetails = User.withUsername(id)
                                    .password(password)
                                    .authorities(role)
                                    .build();

      return Optional.of(userDetails);
    }, username);
  }

  public void addAccessToken(String userId, String accessToken) {
    jdbcTemplate.update("""
            INSERT INTO access_tokens (token, user_id)
            VALUES (?, ?)
            """,
        accessToken, userId
    );
  }
}