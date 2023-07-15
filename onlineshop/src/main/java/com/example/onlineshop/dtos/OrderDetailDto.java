package com.example.onlineshop.dtos;

import java.util.List;

public record OrderDetailDto(
    String id,
    String title,
    List<OrderLineItemDto> lineItems,
    Long totalPrice,
    String status,
    String orderedAt
) {
}
