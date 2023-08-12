package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.dtos.AdminCategoryListDto;
import com.example.onlineshop.models.Category;
import com.example.onlineshop.security.AdminRequired;
import com.example.onlineshop.services.GetCategoryListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AdminRequired
@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {
  private final GetCategoryListService getCategoryListService;

  public AdminCategoryController(
      GetCategoryListService getCategoryListService) {
    this.getCategoryListService = getCategoryListService;
  }

  @GetMapping
  public AdminCategoryListDto list() {
    List<Category> categories = getCategoryListService.getAllCategories();
    return AdminCategoryListDto.of(categories);
  }
}