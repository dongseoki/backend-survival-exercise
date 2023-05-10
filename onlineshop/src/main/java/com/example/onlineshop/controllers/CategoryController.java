package com.example.onlineshop.controllers;

import com.example.onlineshop.dtos.CategoryDto;
import com.example.onlineshop.dtos.CategoryListDto;
import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.services.GetCategoryListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
  private final GetCategoryListService getCategoryListService;

  public CategoryController(GetCategoryListService getCategoryListService) {
    this.getCategoryListService = getCategoryListService;
  }

  @GetMapping
  public CategoryListDto list() {
    List<Category> categories = getCategoryListService.getCategories();

    List<CategoryDto> categoryDtos = categories.stream()
                                               .map(category -> mapToDto(category))
                                               .toList();

    return new CategoryListDto(categoryDtos);
  }

  private CategoryDto mapToDto(Category category) {
    return new CategoryDto(category.id().toString(), category.name());
  }
}