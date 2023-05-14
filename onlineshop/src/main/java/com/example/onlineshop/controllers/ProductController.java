package com.example.onlineshop.controllers;

import com.example.onlineshop.dtos.ProductListDto;
import com.example.onlineshop.services.GetProductListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final GetProductListService getProductListService;

  public ProductController(GetProductListService getProductListService) {
    this.getProductListService = getProductListService;
  }

  @GetMapping
  public ProductListDto list(
      @RequestParam(required = false) String categoryId
  ) {
    return getProductListService.getProductListDto(categoryId);
  }
}
