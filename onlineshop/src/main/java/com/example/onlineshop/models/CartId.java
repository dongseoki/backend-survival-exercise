package com.example.onlineshop.models;

public class CartId extends EntityId {
  private CartId() {
    super();
  }

  public CartId(String value) {
    super(value);
  }

  public static CartId generate() {
    return new CartId(newTsid());
  }

  public static CartId of(String cartId) {
    return new CartId(cartId);
  }
}
