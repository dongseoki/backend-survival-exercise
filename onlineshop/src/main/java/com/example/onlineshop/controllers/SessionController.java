package com.example.onlineshop.controllers;

import com.example.onlineshop.dtos.LoginRequestDto;
import com.example.onlineshop.dtos.LoginResultDto;
import com.example.onlineshop.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
  private final LoginService loginService;

  public SessionController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LoginResultDto login(@RequestBody LoginRequestDto loginRequestDto) {
    String accessToken = loginService.login(
        loginRequestDto.email(), loginRequestDto.password());

    return new LoginResultDto(accessToken);
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String loginFailed() {
    return "Bad Request";
  }
}