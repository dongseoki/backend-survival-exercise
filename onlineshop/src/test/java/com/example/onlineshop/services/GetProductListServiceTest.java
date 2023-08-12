package com.example.onlineshop.services;

import com.example.onlineshop.Fixtures;
import com.example.onlineshop.dtos.AdminProductListDto;
import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.repositories.CategoryRepository;
import com.example.onlineshop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductListServiceTest {
  private ProductRepository productRepository;

  private CategoryRepository categoryRepository;

  private GetProductListService getProductListService;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    categoryRepository = mock(CategoryRepository.class);

    getProductListService = new GetProductListService(
        productRepository, categoryRepository);
  }

  @Test
  void getAdminProductListDto() {
    Product product = Fixtures.product("맨투맨");
    CategoryId categoryId = product.categoryId();
    Category category = new Category(categoryId, "카테고리", false);

    given(productRepository.findAllByOrderByIdAsc())
        .willReturn(List.of(product));

    given(categoryRepository.findById(categoryId))
        .willReturn(Optional.of(category));

    AdminProductListDto adminProductListDto =
        getProductListService.getAdminProductListDto();

    assertThat(adminProductListDto.products()).hasSize(1);
  }
}