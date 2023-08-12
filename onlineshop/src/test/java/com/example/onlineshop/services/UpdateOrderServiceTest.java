package com.example.onlineshop.services;

import com.example.onlineshop.Fixtures;
import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.OrderId;
import com.example.onlineshop.models.OrderStatus;
import com.example.onlineshop.models.User;
import com.example.onlineshop.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateOrderServiceTest {
  private OrderRepository orderRepository;

  private UpdateOrderService updateOrderService;

  @BeforeEach
  void setUp() {
    orderRepository = mock(OrderRepository.class);

    updateOrderService = new UpdateOrderService(orderRepository);
  }

  @Test
  void updateOrderStatus() {
    User user = Fixtures.user("tester");
    Order order = Fixtures.order(user);

    OrderId orderId = order.id();
    OrderStatus newStatus = OrderStatus.COMPLETE;

    given(orderRepository.findById(orderId)).willReturn(Optional.of(order));

    assertThat(order.status()).isNotEqualTo(newStatus);

    updateOrderService.updateOrderStatus(orderId, newStatus);

    assertThat(order.status()).isEqualTo(newStatus);
  }
}