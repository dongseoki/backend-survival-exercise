package com.example.onlineshop.dtos;

import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.Product;

public record ProductSummaryDto(
    String id,
    CategoryDto category,
    ImageDto thumbnail,
    String name,
    Long price
) {
  public static ProductSummaryDto of(Product product, Category category) {
    return new ProductSummaryDto(
        product.id(),
        CategoryDto.of(category),
        ImageDto.of(product.images().stream().findFirst().get().url()),
        product.name(),
        product.price().amount());
  }
}
