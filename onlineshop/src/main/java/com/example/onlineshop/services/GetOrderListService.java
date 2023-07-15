package com.example.onlineshop.services;

import com.example.onlineshop.dtos.OrderListDto;
import com.example.onlineshop.dtos.OrderSummaryDto;
import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetOrderListService {
  private final OrderRepository orderRepository;

  public GetOrderListService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
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
}
