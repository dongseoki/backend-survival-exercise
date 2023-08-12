package com.example.onlineshop.services;

import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.models.Image;
import com.example.onlineshop.models.ImageId;
import com.example.onlineshop.models.Money;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductOption;
import com.example.onlineshop.models.ProductOptionId;
import com.example.onlineshop.models.ProductOptionItem;
import com.example.onlineshop.models.ProductOptionItemId;
import com.example.onlineshop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateProductServiceTest {
  private ProductRepository productRepository;

  private CreateProductService createProductService;

  @BeforeEach
  void setUpMockObjects() {
    productRepository = mock(ProductRepository.class);

    createProductService = new CreateProductService(productRepository);
  }

  @Test
  void createProduct() {
    CategoryId categoryId = CategoryId.generate();
    List<Image> images = List.of(
        new Image(ImageId.generate(), "http://example.com/image.jpg")
    );
    String name = "New Product";
    Money price = new Money(100_000L);
    List<ProductOption> options = List.of(
        new ProductOption(
            ProductOptionId.generate(),
            "Option #1",
            List.of(
                new ProductOptionItem(
                    ProductOptionItemId.generate(),
                    "Item #1"
                )
            )
        )
    );
    String description = "Description";

    Product product = createProductService.createProduct(
        categoryId, images, name, price, options, description);

    assertThat(product.name()).isEqualTo(name);

    verify(productRepository).save(any());
  }
}