package com.example.onlineshop.services;

import com.example.onlineshop.dtos.AdminProductListDto;
import com.example.onlineshop.dtos.AdminProductSummaryDto;
import com.example.onlineshop.dtos.ProductListDto;
import com.example.onlineshop.dtos.ProductSummaryDto;
import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.repositories.CategoryRepository;
import com.example.onlineshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductListService {
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  public GetProductListService(ProductRepository productRepository,
                               CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  public ProductListDto getProductListDto(String categoryId) {
    List<Product> products;
    if (categoryId == null) {
      products = productRepository.findAllByHiddenIsFalseOrderByIdAsc();
    } else {
      products = productRepository.findAllByCategoryIdAndHiddenIsFalseOrderByIdAsc(
          CategoryId.of(categoryId));
    }


    List<ProductSummaryDto> productSummaryDtos = products.stream()
                                                         .map(product -> {
                                                           Category category = categoryRepository
                                                               .findById(product.categoryId())
                                                               .get();
                                                           return ProductSummaryDto.of(product,
                                                               category);
                                                         })
                                                         .toList();

    return new ProductListDto(productSummaryDtos);
  }

  public AdminProductListDto getAdminProductListDto() {
    List<Product> products = productRepository.findAllByOrderByIdAsc();

    List<AdminProductSummaryDto> productSummaryDtos = products.stream()
                                                              .map(product -> {
                                                                // TODO 이런거 N+1 임. 추후 효율적으로 개선 가능.
                                                                // GetOrderListService.getAdminOrderList 참고.
                                                                Category category =
                                                                    categoryRepository
                                                                        .findById(
                                                                            product.categoryId())
                                                                        .orElseThrow();
                                                                return AdminProductSummaryDto.of(
                                                                    product, category);
                                                              })
                                                              .toList();

    return new AdminProductListDto(productSummaryDtos);
  }
}
