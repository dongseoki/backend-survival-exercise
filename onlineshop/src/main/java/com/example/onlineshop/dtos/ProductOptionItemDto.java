package com.example.onlineshop.dtos;

import com.example.onlineshop.models.ProductOptionItem;

public record ProductOptionItemDto(
    String id,
    String name
) {
  public static ProductOptionItemDto of(ProductOptionItem productOptionItem) {
    return new ProductOptionItemDto(
        productOptionItem.id().toString(),
        productOptionItem.name());
  }
}
