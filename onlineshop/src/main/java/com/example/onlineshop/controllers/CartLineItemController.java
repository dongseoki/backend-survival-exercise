package com.example.onlineshop.controllers;

import com.example.onlineshop.annotations.UserRequired;
import com.example.onlineshop.dtos.AddProductToCartDto;
import com.example.onlineshop.models.AuthUser;
import com.example.onlineshop.models.CartLineItemOption;
import com.example.onlineshop.models.ProductId;
import com.example.onlineshop.models.ProductOptionId;
import com.example.onlineshop.models.ProductOptionItemId;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.services.AddProductToCartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@UserRequired
@RestController
@RequestMapping("/cart/line-items")
public class CartLineItemController {
  private final AddProductToCartService addProductToCartService;

  public CartLineItemController(
      AddProductToCartService addProductToCartService) {
    this.addProductToCartService = addProductToCartService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String create(
      Authentication authentication,
      @Valid @RequestBody AddProductToCartDto requestDto) {
    AuthUser authUser = (AuthUser) authentication.getPrincipal();

    UserId userId = new UserId(authUser.id());
    ProductId productId = new ProductId(requestDto.productId());
    Set<CartLineItemOption> options = requestDto.options().stream()
                                                .map(option -> new CartLineItemOption(
                                                    new ProductOptionId(option.id()),
                                                    new ProductOptionItemId(option.itemId())
                                                ))
                                                .collect(Collectors.toSet());
    int quantity = requestDto.quantity();

    addProductToCartService.addProductToCart(
        userId, productId, options, quantity);

    return "Created";
  }
}
