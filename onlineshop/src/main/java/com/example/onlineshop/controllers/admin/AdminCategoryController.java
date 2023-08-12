package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.dtos.AdminCategoryDetailDto;
import com.example.onlineshop.dtos.AdminCategoryListDto;
import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.security.AdminRequired;
import com.example.onlineshop.services.GetCategoryDetailService;
import com.example.onlineshop.services.GetCategoryListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AdminRequired
@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {
  private final GetCategoryListService getCategoryListService;
  private final GetCategoryDetailService getCategoryDetailService;

  public AdminCategoryController(
      GetCategoryListService getCategoryListService,
      GetCategoryDetailService getCategoryDetailService) {
    this.getCategoryListService = getCategoryListService;
    this.getCategoryDetailService = getCategoryDetailService;
  }

  @GetMapping
  public AdminCategoryListDto list() {
    List<Category> categories = getCategoryListService.getAllCategories();
    return AdminCategoryListDto.of(categories);
  }

  @GetMapping("/{id}")
  public AdminCategoryDetailDto detail(@PathVariable String id) {
    CategoryId categoryId = new CategoryId(id);
    Category category = getCategoryDetailService.getCategory(categoryId);
    return AdminCategoryDetailDto.of(category);
  }
}