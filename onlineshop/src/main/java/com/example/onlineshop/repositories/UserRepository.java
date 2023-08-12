package com.example.onlineshop.repositories;

import com.example.onlineshop.models.User;
import com.example.onlineshop.models.UserId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, UserId> {
  boolean existsByEmail(String email);

  List<User> findAllByOrderByIdDesc();

  List<User> findAllByIdIn(List<UserId> id);
}
