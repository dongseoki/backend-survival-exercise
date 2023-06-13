package com.example.onlineshop.dtos;

import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.Product;

import java.util.List;
import java.util.stream.IntStream;

public record ProductDetailDto(
    String id,
    CategoryDto category,
    List<ImageDto> images,
    String name,
    Long price,
    List<ProductOptionDto> options,
    String description
) {
  public static ProductDetailDto of(Product product, Category category) {
    return new ProductDetailDto(
        product.id().toString(),
        CategoryDto.of(category),
        product.images().stream().map(image -> ImageDto.of(image.url())).toList(),
        product.name(),
        product.price().amount(),
        IntStream.range(0, product.optionSize())
                 .mapToObj(i -> ProductOptionDto.of(product.option(i)))
                 .toList(),
        product.description());
  }
}
