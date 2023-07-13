package com.example.onlineshop.infrastructures.dtos;

public record PortonePaymentDto(
    Response response
) {
  public record Response(
      Long amount
  ) {
  }
}
