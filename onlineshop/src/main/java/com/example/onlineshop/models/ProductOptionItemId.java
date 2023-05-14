package com.example.onlineshop.models;

import jakarta.persistence.Column;

import java.io.Serializable;

public class ProductOptionItemId implements Serializable {
  @Column(name = "id")
  private String value;

  public ProductOptionItemId() {
  }

  public ProductOptionItemId(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
