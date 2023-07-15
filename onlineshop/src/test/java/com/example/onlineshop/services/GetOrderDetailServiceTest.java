package com.example.onlineshop.services;

import com.example.onlineshop.Fixtures;
import com.example.onlineshop.dtos.OrderDetailDto;
import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.OrderId;
import com.example.onlineshop.models.OrderLineItem;
import com.example.onlineshop.models.OrderLineItemId;
import com.example.onlineshop.models.OrderStatus;
import com.example.onlineshop.models.Payment;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.Receiver;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.example.onlineshop.utils.TestUtils.createOrderOptions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetOrderDetailServiceTest {
  private OrderRepository orderRepository;

  private GetOrderDetailService getOrderDetailService;

  @BeforeEach
  void setUp() {
    orderRepository = mock(OrderRepository.class);

    getOrderDetailService = new GetOrderDetailService(orderRepository);
  }

  @Test
  void getOrderDetail() {
    UserId userId = UserId.generate();
    OrderId orderId = OrderId.generate();

    Product product = Fixtures.product("맨투맨");

    List<OrderLineItem> lineItems = List.of(
        new OrderLineItem(
            OrderLineItemId.generate(),
            product,
            createOrderOptions(product, new int[] {0, 0}),
            1
        )
    );

    Receiver receiver = Fixtures.receiver("홍길동");
    Payment payment = Fixtures.payment();

    Order order = new Order(OrderId.generate(), UserId.generate(),
        lineItems, receiver, payment, OrderStatus.PAID);

    given(orderRepository.findByIdAndUserId(orderId, userId))
        .willReturn(Optional.of(order));

    OrderDetailDto orderDetailDto =
        getOrderDetailService.getOrderDetail(orderId, userId);

    assertThat(orderDetailDto.lineItems()).hasSize(1);
  }
}