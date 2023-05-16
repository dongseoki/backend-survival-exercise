package com.example.onlineshop.services;

import com.example.onlineshop.dtos.ProductDetailDto;
import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductId;
import com.example.onlineshop.repositories.CategoryRepository;
import com.example.onlineshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class GetProductDetailService {
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  public GetProductDetailService(CategoryRepository categoryRepository,
                                 ProductRepository productRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
  }

  public ProductDetailDto getProductDetailDto(String productId) {
    ProductId id = new ProductId(productId);
    Product product = productRepository.findById(id).orElseThrow();

    Category category = categoryRepository.findById(product.categoryId()).orElseThrow();

    return ProductDetailDto.of(product, category);
  }
}
