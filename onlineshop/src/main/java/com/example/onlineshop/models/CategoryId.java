package com.example.onlineshop.models;

import jakarta.persistence.Column;

public class CategoryId {
  @Column(name = "id")
  private String value;

  public CategoryId() {
  }

  public CategoryId(String value) {
    this.value = value;
  }

  public static CategoryId of(String categoryId) {
    return new CategoryId(categoryId);
  }

  @Override
  public String toString() {
    return value;
  }

}
