package com.example.securityexample.dtos;

public record LoginRequestDto(
    String username,
    String password
) {
}