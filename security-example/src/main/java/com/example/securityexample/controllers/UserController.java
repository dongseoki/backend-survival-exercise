package com.example.securityexample.controllers;

import com.example.securityexample.dtos.SignupRequestDto;
import com.example.securityexample.dtos.SignupResultDto;
import com.example.securityexample.exceptions.UserAlreadyExistsException;
import com.example.securityexample.services.SignupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  private final SignupService signupService;

  public UserController(SignupService signupService) {
    this.signupService = signupService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SignupResultDto signup(
      @Valid @RequestBody SignupRequestDto signupRequestDto) throws UserAlreadyExistsException {
    String accessToken = signupService.signup(
        signupRequestDto.username().trim(),
        signupRequestDto.password().trim());

    return new SignupResultDto(accessToken);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String alreadyExists() {
    return "User Already Exists";
  }
}