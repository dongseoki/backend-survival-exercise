package com.example.onlineshop.repositories;

import com.example.onlineshop.models.User;
import com.example.onlineshop.models.UserId;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UserId> {
  boolean existsByEmail(String email);
}
