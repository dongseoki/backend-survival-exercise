package com.example.onlineshop.dtos;

import jakarta.validation.constraints.NotBlank;

public record AdminUpdateOrderDto(
    @NotBlank
    String status
) {
}