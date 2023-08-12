package com.example.onlineshop.dtos;

import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.Product;

public record AdminProductSummaryDto(
    String id,
    CategoryDto category,
    ImageDto thumbnail,
    String name,
    Long price,
    boolean hidden
) {
  public static AdminProductSummaryDto of(
      Product product, Category category) {
    return new AdminProductSummaryDto(
        product.id().toString(),
        CategoryDto.of(category),
        ImageDto.of(product.image(0).url()),
        product.name(),
        product.price().asLong(),
        product.hidden()
    );
  }
}