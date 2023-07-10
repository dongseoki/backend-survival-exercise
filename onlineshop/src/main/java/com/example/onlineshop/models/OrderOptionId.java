package com.example.onlineshop.models;

public class OrderOptionId extends EntityId {
  private OrderOptionId() {
    super();
  }

  public OrderOptionId(String value) {
    super(value);
  }

  public static OrderOptionId generate() {
    return new OrderOptionId(newTsid());
  }

  public static OrderOptionId of(String categoryId) {
    return new OrderOptionId(categoryId);
  }
}
