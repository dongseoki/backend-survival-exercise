package com.example.onlineshop.services;

import com.example.onlineshop.Fixtures;
import com.example.onlineshop.dtos.CartDto;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCartServiceTest {
  private GetCartService getCartService;

  private CartRepository cartRepository;

  private ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    cartRepository = mock(CartRepository.class);

    productRepository = mock(ProductRepository.class);

    getCartService = new GetCartService(cartRepository, productRepository);
  }

  @Test
  @DisplayName("getCartDto - when cart doesn't exist")
  void getCartDto() {
    UserId userId = new UserId("USER-ID");

    CartDto cartDto = getCartService.getCartDto(userId);

    assertThat(cartDto.lineItems()).isEmpty();

    assertThat(cartDto.totalPrice()).isEqualTo(0L);
  }

  @Test
  @DisplayName("getCartDto - when cart already exists")
  void getCartDtoWhenCartExists() {
    UserId userId = new UserId("USER-ID");

    Cart cart = new Cart(userId);

    Product product = Fixtures.product("맨투맨");

    Set<CartLineItemOption> options = Set.of(
        createCartLineItemOption(product, 0, 0),
        createCartLineItemOption(product, 1, 0)
    );

    cart.addProduct(product, options, 1);

    given(cartRepository.findByUserId(userId))
        .willReturn(Optional.of(cart));

    given(productRepository.findById(product.id()))
        .willReturn(Optional.of(product));

    CartDto cartDto = getCartService.getCartDto(userId);

    assertThat(cartDto.totalPrice()).isEqualTo(product.price().asLong());
  }
}