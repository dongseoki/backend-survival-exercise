package com.example.onlineshop.repositories;

import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.CategoryId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository
    extends CrudRepository<Category, CategoryId> {
  public List<Category> findAll();
}
