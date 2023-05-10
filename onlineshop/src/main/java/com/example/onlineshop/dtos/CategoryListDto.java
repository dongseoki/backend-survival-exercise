package com.example.onlineshop.dtos;

import java.util.List;

public record CategoryListDto(
    List<CategoryDto> categories
) {
}
