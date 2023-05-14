package com.example.onlineshop.models;

import jakarta.persistence.Column;

import java.io.Serializable;

public class ProductId implements Serializable {
  @Column(name = "id")
  private String value;

  public ProductId() {
  }

  public ProductId(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
