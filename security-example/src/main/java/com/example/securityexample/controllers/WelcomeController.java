package com.example.securityexample.controllers;

import com.example.securityexample.config.RoleAdminRequired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
  @GetMapping("/")
  public String home() {
    return "Hello, world!";
  }

  @GetMapping("/admin")
  @RoleAdminRequired()
//  @Secured({"ROLE_ADMIN"})
  public String admin() {
    return "Hello, world!";
  }

  @GetMapping("/user")
  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  public String user() {
    return "Hello, world!";
  }
}