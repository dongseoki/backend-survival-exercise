package com.example.onlineshop.services;

import com.example.onlineshop.Fixtures;
import com.example.onlineshop.dtos.AdminProductDetailDto;
import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductId;
import com.example.onlineshop.repositories.CategoryRepository;
import com.example.onlineshop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductDetailServiceTest {
  private ProductRepository productRepository;

  private CategoryRepository categoryRepository;

  private GetProductDetailService getProductDeailService;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    categoryRepository = mock(CategoryRepository.class);

    getProductDeailService = new GetProductDetailService(
        productRepository, categoryRepository);
  }

  @Test
  void getAdminProductDetailDto() {
    Product product = Fixtures.product("맨투맨");
    ProductId productId = product.id();

    CategoryId categoryId = product.categoryId();
    Category category = new Category(categoryId, "카테고리", false);

    given(productRepository.findById(productId))
        .willReturn(Optional.of(product));

    given(categoryRepository.findById(categoryId))
        .willReturn(Optional.of(category));

    AdminProductDetailDto productDto =
        getProductDeailService.getAdminProductDetailDto(productId);

    assertThat(productDto.name()).isEqualTo("맨투맨");
  }
}