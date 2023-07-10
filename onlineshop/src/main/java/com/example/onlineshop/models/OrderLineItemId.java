package com.example.onlineshop.models;

public class OrderLineItemId extends EntityId {
  private OrderLineItemId() {
    super();
  }

  public OrderLineItemId(String value) {
    super(value);
  }

  public static OrderLineItemId generate() {
    return new OrderLineItemId(newTsid());
  }

  public static OrderLineItemId of(String categoryId) {
    return new OrderLineItemId(categoryId);
  }
}
