package com.example.onlineshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_option_items")
public class ProductOptionItem {
  @EmbeddedId
  private ProductOptionItemId id;

  @Column(name = "name")
  private String name;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public ProductOptionItem(ProductOptionItemId productOptionItemId, String name) {
    this.id = productOptionItemId;
    this.name = name;
  }

  public ProductOptionItem() {
  }

  public ProductOptionItemId id() {
    return id;
  }

  public String name() {
    return name;
  }

  public void changeName(String name) {
    this.name = name;
  }
}