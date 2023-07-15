package com.example.onlineshop.dtos;

import java.util.List;

public record OrderListDto(
    List<OrderSummaryDto> orders
) {
}
