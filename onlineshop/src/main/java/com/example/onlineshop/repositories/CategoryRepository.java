package com.example.onlineshop.repositories;

import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.CategoryId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository
    extends CrudRepository<Category, CategoryId> {
  public List<Category> findAll();
}
