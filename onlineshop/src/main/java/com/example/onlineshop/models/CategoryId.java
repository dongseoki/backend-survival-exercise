package com.example.onlineshop.models;

public class CategoryId extends EntityId {
  private CategoryId() {
    super();
  }

  public CategoryId(String value) {
    super(value);
  }

  public static CategoryId generate() {
    return new CategoryId(newTsid());
  }

  public static CategoryId of(String categoryId) {
    return new CategoryId(categoryId);
  }
}
