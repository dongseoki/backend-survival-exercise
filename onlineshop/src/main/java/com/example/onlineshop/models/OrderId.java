package com.example.onlineshop.models;

public class OrderId extends EntityId {
  private OrderId() {
    super();
  }

  public OrderId(String value) {
    super(value);
  }

  public static OrderId generate() {
    return new OrderId(newTsid());
  }

  public static OrderId of(String categoryId) {
    return new OrderId(categoryId);
  }
}
