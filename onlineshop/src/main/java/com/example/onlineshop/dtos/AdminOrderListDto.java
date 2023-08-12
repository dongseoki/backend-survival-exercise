package com.example.onlineshop.dtos;

import java.util.List;

public record AdminOrderListDto(
    List<AdminOrderSummaryDto> orders
) {
}
