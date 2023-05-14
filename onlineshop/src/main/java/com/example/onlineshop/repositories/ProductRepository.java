package com.example.onlineshop.repositories;

import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId> {
  List<Product> findAllByCategoryId(CategoryId categoryId);
}
