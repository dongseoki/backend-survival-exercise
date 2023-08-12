package com.example.onlineshop.services;

import com.example.onlineshop.dtos.AdminOrderListDto;
import com.example.onlineshop.dtos.AdminOrderSummaryDto;
import com.example.onlineshop.dtos.OrderListDto;
import com.example.onlineshop.dtos.OrderSummaryDto;
import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.User;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.repositories.OrderRepository;
import com.example.onlineshop.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetOrderListService {
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;

  public GetOrderListService(OrderRepository orderRepository, UserRepository userRepository) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
  }

  public OrderListDto getOrderList(UserId userId) {
    List<Order> orders =
        orderRepository.findAllByUserIdOrderByIdDesc(userId);

    return new OrderListDto(
        orders.stream()
              .map(OrderSummaryDto::of)
              .toList()
    );
  }

  public AdminOrderListDto getAdminOrderList() {
    List<Order> orders = orderRepository.findAllByOrderByIdDesc();
    List<UserId> userIds = orders.stream()
                                 .map(Order::userId)
                                 .toList();
    List<User> users = userRepository.findAllByIdIn(userIds);

    return new AdminOrderListDto(
        orders.stream()
              .map(order -> {
                User user = users.stream()
                                 .filter(u -> u.id().equals(order.userId()))
                                 .findFirst()
                                 .orElseThrow();
                return AdminOrderSummaryDto.of(order, user);
              })
              .toList()
    );
  }
}
