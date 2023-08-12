package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.controllers.ControllerTest;
import com.example.onlineshop.dtos.AdminProductDetailDto;
import com.example.onlineshop.dtos.AdminProductListDto;
import com.example.onlineshop.dtos.AdminProductSummaryDto;
import com.example.onlineshop.dtos.CategoryDto;
import com.example.onlineshop.dtos.ImageDto;
import com.example.onlineshop.models.ProductId;
import com.example.onlineshop.services.GetProductDetailService;
import com.example.onlineshop.services.GetProductListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminProductController.class)
class AdminProductControllerTest extends ControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetProductListService getProductListService;

  @MockBean
  private GetProductDetailService getProductDetailService;

  @Test
  @DisplayName("GET /admin/products")
  void list() throws Exception {
    AdminProductSummaryDto productDto = new AdminProductSummaryDto(
        "PRODUCT-ID",
        new CategoryDto("CATEGORY-ID", "Category"),
        new ImageDto("http://example.com/01.jpg"),
        "Product",
        100_000L,
        false
    );

    given(getProductListService.getAdminProductListDto())
        .willReturn(new AdminProductListDto(List.of(productDto)));

    mockMvc.perform(get("/admin/products")
               .header("Authorization", "Bearer " + adminAccessToken))
           .andExpect(status().isOk());
  }

  @Test
  @DisplayName("GET /admin/products/{id}")
  void detail() throws Exception {
    ProductId productId = new ProductId("1234");

    AdminProductDetailDto productDto = new AdminProductDetailDto(
        productId.toString(),
        new CategoryDto("CATEGORY-ID", "Category"),
        List.of(new ImageDto("http://example.com/01.jpg")),
        "Product",
        100_000L,
        List.of(),
        "Description",
        false
    );

    given(getProductDetailService.getAdminProductDetailDto(productId))
        .willReturn(productDto);

    mockMvc.perform(get("/admin/products/" + productId)
               .header("Authorization", "Bearer " + adminAccessToken))
           .andExpect(status().isOk());
  }
}