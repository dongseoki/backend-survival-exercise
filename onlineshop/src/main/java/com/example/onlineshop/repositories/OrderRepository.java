package com.example.onlineshop.repositories;

import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.OrderId;
import com.example.onlineshop.models.UserId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, OrderId> {
  List<Order> findAllByUserIdOrderByIdDesc(UserId userId);

  Optional<Order> findByIdAndUserId(OrderId orderId, UserId userId);

  List<Order> findAllByOrderByIdDesc();
}