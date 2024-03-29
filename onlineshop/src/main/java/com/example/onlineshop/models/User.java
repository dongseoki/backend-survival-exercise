package com.example.onlineshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
  @EmbeddedId
  private UserId id;

  @Column(name = "email")
  private String email;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String encodedPassword;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public User() {
  }

  public User(UserId id, String email, String name, Role role) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.role = role;
  }

  public UserId id() {
    return id;
  }

  public String name() {
    return name;
  }

  public void changePassword(String password, PasswordEncoder passwordEncoder) {
    this.encodedPassword = passwordEncoder.encode(password);
  }

  public boolean isAdmin() {
    return role == (Role.ROLE_ADMIN);
  }

  public String email() {
    return email;
  }

  public Role role() {
    return role;
  }
}
