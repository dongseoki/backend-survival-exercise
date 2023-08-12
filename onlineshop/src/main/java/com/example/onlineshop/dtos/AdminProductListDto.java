package com.example.onlineshop.dtos;

import java.util.List;

public record AdminProductListDto(
    List<AdminProductSummaryDto> products
) {
}