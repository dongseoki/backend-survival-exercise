package com.example.onlineshop.services;

import com.example.onlineshop.Fixtures;
import com.example.onlineshop.models.Cart;
import com.example.onlineshop.models.CartLineItemOption;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.repositories.CartRepository;
import com.example.onlineshop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static com.example.onlineshop.utils.TestUtils.createCartLineItemOption;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AddProductToCartServiceTest {
  private AddProductToCartService addProductToCartService;

  private CartRepository cartRepository;
  private ProductRepository productRepository;

  private Product product;
  private Set<CartLineItemOption> options;
  private int quantity;

  @BeforeEach
  void setUpMockObjects() {
    cartRepository = mock(CartRepository.class);

    productRepository = mock(ProductRepository.class);

    addProductToCartService = new AddProductToCartService(
        cartRepository, productRepository);
  }

  @BeforeEach
  void setUpFixtures() {
    product = Fixtures.product("맨투맨");

    options = Set.of(
        createCartLineItemOption(product, 0, 0),
        createCartLineItemOption(product, 1, 0)
    );

    quantity = 1;
  }

  @Test
  @DisplayName("addProductToCart - when cart exists")
  void addProductToCart() {
    UserId userId = new UserId("USER-ID");

    Cart cart = new Cart(userId);

    given(cartRepository.findByUserId(userId))
        .willReturn(Optional.of(cart));

    given(productRepository.findById(product.id()))
        .willReturn(Optional.of(product));

    addProductToCartService.addProductToCart(
        userId, product.id(), options, quantity);

    assertThat(cart.lineItemSize()).isEqualTo(1);
  }

  @Test
  @DisplayName("addProductToCart - when cart doesn't exist")
  void addProductToCartWithoutCart() {
    UserId userId = new UserId("USER-ID");

    given(cartRepository.findByUserId(userId))
        .willReturn(Optional.empty());

    given(productRepository.findById(product.id()))
        .willReturn(Optional.of(product));

    addProductToCartService.addProductToCart(
        userId, product.id(), options, quantity);

    verify(cartRepository).save(any(Cart.class));
  }
}