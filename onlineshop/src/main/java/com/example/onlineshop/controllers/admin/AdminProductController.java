package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.dtos.AdminProductDetailDto;
import com.example.onlineshop.dtos.AdminProductListDto;
import com.example.onlineshop.models.ProductId;
import com.example.onlineshop.security.AdminRequired;
import com.example.onlineshop.services.GetProductDetailService;
import com.example.onlineshop.services.GetProductListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AdminRequired
@RestController
@RequestMapping("/admin/products")
public class AdminProductController {
  private final GetProductListService getProductListService;

  private final GetProductDetailService getProductDetailService;

  public AdminProductController(GetProductListService getProductListService,
                                GetProductDetailService getProductDetailService) {
    this.getProductListService = getProductListService;
    this.getProductDetailService = getProductDetailService;
  }

  @GetMapping
  public AdminProductListDto list() {
    return getProductListService.getAdminProductListDto();
  }

  @GetMapping("/{id}")
  public AdminProductDetailDto detail(@PathVariable String id) {
    ProductId productId = new ProductId(id);
    return getProductDetailService.getAdminProductDetailDto(productId);
  }
}
