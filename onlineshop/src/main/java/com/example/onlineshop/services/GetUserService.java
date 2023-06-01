package com.example.onlineshop.services;

import com.example.onlineshop.models.User;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetUserService {
  private final UserRepository userRepository;

  public GetUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUser(UserId id) {
    return userRepository.findById(id)
                         .orElseThrow();
  }
}