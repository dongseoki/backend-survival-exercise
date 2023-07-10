package com.example.onlineshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Money {
  public static final Money ZERO = new Money(0L);
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

  public long asLong() {
    return amount;
  }

  public Money times(int quantity) {
    return new Money(amount * quantity);
  }

  public Money plus(Money money) {
    return new Money(amount + money.amount);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Money money)) {
      return false;
    }

    return Objects.equals(amount, money.amount);
  }

  @Override
  public int hashCode() {
    return amount != null ? amount.hashCode() : 0;
  }
}
