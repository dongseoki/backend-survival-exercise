package com.example.onlineshop.controllers;

import com.example.onlineshop.dtos.CartDto;
import com.example.onlineshop.models.AuthUser;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.security.UserRequired;
import com.example.onlineshop.services.GetCartService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@UserRequired
@RestController
@RequestMapping("/cart")
public class CartController {
  private final GetCartService getCartService;

  public CartController(GetCartService getCartService) {
    this.getCartService = getCartService;
  }

  @GetMapping
  public CartDto detail(Authentication authentication) {
    AuthUser authUser = (AuthUser) authentication.getPrincipal();

    UserId userId = new UserId(authUser.id());

    return getCartService.getCartDto(userId);
  }
}
