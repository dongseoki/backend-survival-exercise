package com.example.securityexample.dtos;

import jakarta.validation.constraints.NotBlank;

public record SignupRequestDto(
    @NotBlank
    String username,
    @NotBlank
    String password) {
}
