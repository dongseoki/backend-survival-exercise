package com.example.onlineshop.models;

import jakarta.persistence.Column;

import java.io.Serializable;

public class ImageId implements Serializable {
  @Column(name = "id")
  private String value;

  public ImageId() {
  }

  public ImageId(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
