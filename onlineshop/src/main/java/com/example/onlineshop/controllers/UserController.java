package com.example.onlineshop.controllers;

import com.example.onlineshop.dtos.SignupRequestDto;
import com.example.onlineshop.dtos.SignupResultDto;
import com.example.onlineshop.dtos.UserDto;
import com.example.onlineshop.models.AuthUser;
import com.example.onlineshop.models.User;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.services.GetUserService;
import com.example.onlineshop.services.SignupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  private final GetUserService getUserService;

  private final SignupService signupService;

  public UserController(GetUserService getUserService, SignupService signupService) {
    this.getUserService = getUserService;
    this.signupService = signupService;
  }

  @GetMapping("/me")
  public UserDto me(Authentication authentication) {
    AuthUser authUser = (AuthUser) authentication.getPrincipal();
    UserId id = new UserId(authUser.id());
    User user = getUserService.getUser(id);
    return UserDto.of(user);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SignupResultDto signup(
      @Valid @RequestBody SignupRequestDto signupRequestDto
  ) {
    String accessToken = signupService.signup(
        signupRequestDto.email().trim(),
        signupRequestDto.name().trim(),
        signupRequestDto.password().trim()
    );

    return new SignupResultDto(accessToken);
  }
}