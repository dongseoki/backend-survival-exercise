package com.example.onlineshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
public class Category {
  @EmbeddedId
  private CategoryId id;

  @Column(name = "name")
  private String name;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column(name = "hidden")
  private boolean hidden;

  private Category() {
  }

  public Category(CategoryId id, String name, boolean hidden) {
    this.id = id;
    this.name = name;
    this.hidden = hidden;
  }

  public Category(CategoryId id, String name) {
    this(id, name, false);
  }

  public CategoryId id() {
    return id;
  }

  public String name() {
    return name;
  }

  public boolean hidden() {
    return hidden;
  }
}
// …(후략)…
