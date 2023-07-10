package com.example.onlineshop.models;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Receiver {
  private String name;

  private Address address;

  private PhoneNumber phoneNumber;

  private Receiver() {

  }

  public Receiver(String name, Address address, PhoneNumber phoneNumber) {
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Receiver other = (Receiver) o;
    return Objects.equals(name, other.name) &&
        Objects.equals(address, other.address) &&
        Objects.equals(phoneNumber, other.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address, phoneNumber);
  }
}
