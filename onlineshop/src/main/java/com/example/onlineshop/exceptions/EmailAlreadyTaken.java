package com.example.onlineshop.exceptions;

public class EmailAlreadyTaken extends RuntimeException {
  public EmailAlreadyTaken(String email) {
    super("Email " + email + " is already taken");
  }
}
