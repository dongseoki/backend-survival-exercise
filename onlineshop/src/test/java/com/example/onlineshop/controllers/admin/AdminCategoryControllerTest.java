package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.controllers.ControllerTest;
import com.example.onlineshop.models.Category;
import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.services.GetCategoryListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminCategoryController.class)
class AdminCategoryControllerTest extends ControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetCategoryListService getCategoryListService;

  @Test
  @DisplayName("GET /admin/categories")
  void list() throws Exception {
    CategoryId id = new CategoryId("0BV000CAT0001");
    Category category = new Category(id, "top");

    given(getCategoryListService.getAllCategories())
        .willReturn(List.of(category));

    mockMvc.perform(get("/admin/categories")
               .header("Authorization", "Bearer " + adminAccessToken))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("top")));
  }
}