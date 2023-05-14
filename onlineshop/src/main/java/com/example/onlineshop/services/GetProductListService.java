package com.example.onlineshop.services;

import com.example.onlineshop.dtos.ProductListDto;
import com.example.onlineshop.dtos.ProductSummaryDto;
import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.repositories.CategoryRepository;
import com.example.onlineshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductListService {
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  public GetProductListService(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
  }

  public ProductListDto getProductListDto(String categoryId) {
    List<Product> products;
    if (categoryId == null) {
      products = productRepository.findAll();
    } else {
      products = productRepository.findAllByCategoryId(CategoryId.of(categoryId));
    }


    List<ProductSummaryDto> productSummaryDtos = products.stream()
                                                         .map(product -> {
                                                           Category category = categoryRepository
                                                               .findById(product.categoryId())
                                                               .get();
                                                           return ProductSummaryDto.of(product,
                                                               category);
                                                         })
                                                         .toList();

    return new ProductListDto(productSummaryDtos);
  }
}
