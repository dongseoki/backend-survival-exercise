package com.example.onlineshop.services;

import com.example.onlineshop.Fixtures;
import com.example.onlineshop.models.Address;
import com.example.onlineshop.models.Cart;
import com.example.onlineshop.models.CartLineItemOption;
import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.Payment;
import com.example.onlineshop.models.PhoneNumber;
import com.example.onlineshop.models.PostalCode;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.Receiver;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.repositories.CartRepository;
import com.example.onlineshop.repositories.OrderRepository;
import com.example.onlineshop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

import static com.example.onlineshop.utils.TestUtils.createCartLineItemOption;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateOrderServiceTest {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CreateOrderService createOrderService;

  private Product product;

  private Set<CartLineItemOption> options;
  private int quantity;

  private Receiver receiver;
  private Payment payment;


  @BeforeEach
  void setUpMockObjects() {
    productRepository = mock(ProductRepository.class);
    cartRepository = mock(CartRepository.class);
    orderRepository = mock(OrderRepository.class);

    createOrderService = new CreateOrderService(
        productRepository, cartRepository, orderRepository);
  }

  @BeforeEach
  void setUpFixtures() {
    product = Fixtures.product("맨투맨");

    options = Set.of(
        createCartLineItemOption(product, 0, 0),
        createCartLineItemOption(product, 1, 0)
    );

    quantity = 1;

    receiver = new Receiver(
        "홍길동",
        new Address(
            "서울특별시 성동구 상원12길 34",
            "ㅇㅇㅇ호",
            new PostalCode("04790")
        ),
        new PhoneNumber("01012345678")
    );

    payment = new Payment(
        "ORDER-20230101-00000001",
        "123456789012"
    );
  }

  @Test
  void createOrder() {
    //given
    UserId userId = UserId.generate();
    Cart cart = new Cart(userId);
    cart.addProduct(product, options, quantity);

    given(cartRepository.findByUserId(userId)).willReturn(Optional.of(cart));

    given(productRepository.findById(product.id())).willReturn(Optional.of(product));

    //when
    Order order = createOrderService.createOrder(userId, receiver, payment);

    //then
    assertThat(order.lineItemSize()).isEqualTo(1);
    verify(orderRepository).save(order);
  }
}