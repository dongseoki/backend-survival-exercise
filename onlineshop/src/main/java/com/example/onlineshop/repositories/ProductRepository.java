package com.example.onlineshop.repositories;

import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, ProductId> {
  List<Product> findAllByHiddenIsFalseOrderByIdAsc();

  List<Product> findAllByCategoryIdAndHiddenIsFalseOrderByIdAsc(
      CategoryId categoryId);

  List<Product> findAllByOrderByIdAsc();
}
