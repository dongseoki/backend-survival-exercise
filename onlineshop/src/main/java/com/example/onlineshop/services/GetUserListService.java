package com.example.onlineshop.services;

import com.example.onlineshop.models.User;
import com.example.onlineshop.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetUserListService {
  private final UserRepository userRepository;

  public GetUserListService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUserList() {
    return userRepository.findAllByOrderByIdDesc();
  }
}