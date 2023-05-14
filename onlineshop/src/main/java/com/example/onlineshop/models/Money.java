package com.example.onlineshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Money {
  @Column(name = "amount")
  private Long amount;

  public Money() {
  }

  public Money(Long amount) {
    this.amount = amount;
  }

  public Long amount() {
    return amount;
  }

}
