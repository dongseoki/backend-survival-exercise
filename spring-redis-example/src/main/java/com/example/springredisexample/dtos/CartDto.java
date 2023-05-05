package com.example.springredisexample.dtos;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash("carts")
public class CartDto {
  @Id
  private String id;

  private List<LineItemDto> lineItems;

  private CartDto() {
  }

  public CartDto(String id, List<LineItemDto> lineItems) {
    this.id = id;
    this.lineItems = lineItems;
  }

  public List<LineItemDto> getLineItems() {
    return lineItems;
  }

  public record LineItemDto(
      String id,
      String productName,
      long unitPrice,
      int quantity,
      long totalPrice
  ) {
  }

  @Override
  public String toString() {
    return "CartDto{" +
        "id='" + id + '\'' +
        ", lineItems=" + lineItems +
        '}';
  }
}