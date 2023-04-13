package com.example.securityexample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
  @GetMapping("/")
  public String home() {
    return "Hello, world!";
  }
}