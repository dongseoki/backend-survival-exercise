package com.example.onlineshop.dtos;

import jakarta.validation.constraints.NotBlank;

public record AdminCreateCategoryDto(
    @NotBlank
    String name
) {
}
