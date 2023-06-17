package com.example.onlineshop.repositories;

import com.example.onlineshop.models.Cart;
import com.example.onlineshop.models.CartId;
import com.example.onlineshop.models.UserId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, CartId> {
  Optional<Cart> findByUserId(UserId userId);
}
