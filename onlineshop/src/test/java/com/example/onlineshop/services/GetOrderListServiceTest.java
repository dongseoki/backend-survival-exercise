package com.example.onlineshop.services;

import com.example.onlineshop.Fixtures;
import com.example.onlineshop.dtos.AdminOrderListDto;
import com.example.onlineshop.dtos.OrderListDto;
import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.OrderId;
import com.example.onlineshop.models.OrderLineItem;
import com.example.onlineshop.models.OrderLineItemId;
import com.example.onlineshop.models.OrderStatus;
import com.example.onlineshop.models.Payment;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.Receiver;
import com.example.onlineshop.models.User;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.repositories.OrderRepository;
import com.example.onlineshop.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.onlineshop.utils.TestUtils.createOrderOptions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetOrderListServiceTest {
  private OrderRepository orderRepository;
  private UserRepository userRepository;

  private GetOrderListService getOrderListService;

  @BeforeEach
  void setUp() {
    orderRepository = mock(OrderRepository.class);
    userRepository = mock(UserRepository.class);

    getOrderListService = new GetOrderListService(orderRepository, userRepository);
  }

  @Test
  void getOrderList() {
    UserId userId = UserId.generate();

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

    given(orderRepository.findAllByUserIdOrderByIdDesc(userId))
        .willReturn(List.of(order));

    OrderListDto orderListDto = getOrderListService.getOrderList(userId);

    assertThat(orderListDto.orders()).hasSize(1);
  }

  @Test
  void getAdminOrderList() {
    User user = Fixtures.user("tester");
    Order order = Fixtures.order(user);

    given(orderRepository.findAllByOrderByIdDesc())
        .willReturn(List.of(order));
    given(userRepository.findAllByIdIn(List.of(user.id())))
        .willReturn(List.of(user));

    AdminOrderListDto ordersDto = getOrderListService.getAdminOrderList();

    assertThat(ordersDto.orders()).hasSize(1);
  }
}