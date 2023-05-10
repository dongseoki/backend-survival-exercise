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

  @Override
  public String toString() {
    return value;
  }

}
