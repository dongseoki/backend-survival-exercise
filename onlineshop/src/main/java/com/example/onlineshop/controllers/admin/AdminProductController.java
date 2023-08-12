package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.dtos.AdminProductListDto;
import com.example.onlineshop.security.AdminRequired;
import com.example.onlineshop.services.GetProductListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AdminRequired
@RestController
@RequestMapping("/admin/products")
public class AdminProductController {
  private final GetProductListService getProductListService;

  public AdminProductController(GetProductListService getProductListService) {
    this.getProductListService = getProductListService;
  }

  @GetMapping
  public AdminProductListDto list() {
    return getProductListService.getAdminProductListDto();
  }
}
