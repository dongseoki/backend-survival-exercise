package com.example.onlineshop.services;

import com.example.onlineshop.dtos.AdminUpdateProductDto;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductId;
import com.example.onlineshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateProductService {
  private final ProductRepository productRepository;

  public UpdateProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void updateProduct(
      ProductId productId, AdminUpdateProductDto productDto) {
    Product product = productRepository.findById(productId)
                                       .orElseThrow();

    product.update(productDto);
  }
}
