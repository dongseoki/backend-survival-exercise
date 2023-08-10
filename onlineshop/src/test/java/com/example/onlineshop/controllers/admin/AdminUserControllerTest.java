package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.controllers.ControllerTest;
import com.example.onlineshop.models.User;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.services.GetUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.onlineshop.models.Role.ROLE_ADMIN;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminUserController.class)
class AdminUserControllerTest extends ControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetUserService getUserService;

  @Test
  @DisplayName("GET /admin/users/me - with admin access token")
  void detailAdmin() throws Exception {
    UserId userId = new UserId(ADMIN_ID);

    User user = new User(userId, "admin@example.com", "Admin", ROLE_ADMIN);

    given(getUserService.getAdminUser(userId)).willReturn(user);

    mockMvc.perform(get("/admin/users/me")
               .header("Authorization", "Bearer " + adminAccessToken))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("name")));
  }

  @Test
  @DisplayName("GET /admin/users/me - with non-admin access token")
  void detailNonAdmin() throws Exception {
    mockMvc.perform(get("/admin/users/me")
               .header("Authorization", "Bearer " + userAccessToken))
           .andExpect(status().isForbidden());
  }
}