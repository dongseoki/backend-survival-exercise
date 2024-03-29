package com.example.onlineshop.services;

import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetCategoryDetailService {
  private final CategoryRepository categoryRepository;

  public GetCategoryDetailService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category getCategory(CategoryId categoryId) {
    return categoryRepository.findById(categoryId)
                             .orElseThrow();
  }
}
