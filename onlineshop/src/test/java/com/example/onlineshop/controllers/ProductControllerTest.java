package com.example.onlineshop.controllers;

import com.example.onlineshop.dtos.CategoryDto;
import com.example.onlineshop.dtos.ProductDetailDto;
import com.example.onlineshop.services.GetProductDetailService;
import com.example.onlineshop.services.GetProductListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetProductDetailService getProductDetailService;

  @MockBean
  private GetProductListService getProductListService;


  @Test
  void detail() throws Exception {
    //given
    CategoryDto categoryDto = new CategoryDto("2", "top");
    ProductDetailDto productDetailDto =
        new ProductDetailDto("1", categoryDto, null, "detailProduct", 10000L, null,
            "This is Sample");
    given(getProductDetailService.getProductDetailDto("1"))
        .willReturn(productDetailDto);

    //when
    mockMvc.perform(get("/products/1"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("top")))
           .andExpect(content().string(containsString("detailProduct")));

    //then
  }
}