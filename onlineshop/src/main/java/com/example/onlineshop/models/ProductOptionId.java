package com.example.onlineshop.models;

import jakarta.persistence.Column;

import java.io.Serializable;

public class ProductOptionId implements Serializable {
  @Column(name = "id")
  private String value;

  public ProductOptionId() {
  }

  public ProductOptionId(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
