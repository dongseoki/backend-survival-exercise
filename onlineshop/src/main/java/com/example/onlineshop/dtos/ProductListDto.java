package com.example.onlineshop.dtos;

import java.util.List;

public record ProductListDto(
    List<ProductSummaryDto> products
) {
}
