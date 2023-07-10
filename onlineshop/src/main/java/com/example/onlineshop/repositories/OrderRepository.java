package com.example.onlineshop.repositories;

import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.OrderId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, OrderId> {
}