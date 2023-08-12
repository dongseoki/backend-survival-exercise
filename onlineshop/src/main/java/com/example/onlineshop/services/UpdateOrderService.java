package com.example.onlineshop.services;

import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.OrderId;
import com.example.onlineshop.models.OrderStatus;
import com.example.onlineshop.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateOrderService {
  private final OrderRepository orderRepository;

  public UpdateOrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void updateOrderStatus(OrderId orderId, OrderStatus status) {
    Order order = orderRepository.findById(orderId)
                                 .orElseThrow();
    order.changeStatus(status);
  }
}