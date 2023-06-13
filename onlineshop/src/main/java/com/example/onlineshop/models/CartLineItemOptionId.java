package com.example.onlineshop.models;

public class CartLineItemOptionId extends EntityId {
  private CartLineItemOptionId() {
    super();
  }

  public CartLineItemOptionId(String value) {
    super(value);
  }

  public static CartLineItemOptionId generate() {
    return new CartLineItemOptionId(newTsid());
  }
}
