package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.Fixtures;
import com.example.onlineshop.controllers.ControllerTest;
import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.OrderId;
import com.example.onlineshop.models.User;
import com.example.onlineshop.services.GetAdminOrderDetailService;
import com.example.onlineshop.services.GetOrderListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminOrderController.class)
class AdminOrderControllerTest extends ControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetOrderListService getOrderListService;

  @MockBean
  private GetAdminOrderDetailService getAdminOrderDetailService;

  @Test
  @DisplayName("GET /admin/orders")
  void list() throws Exception {
    mockMvc.perform(get("/admin/orders")
               .header("Authorization", "Bearer " + adminAccessToken))
           .andExpect(status().isOk());

    verify(getOrderListService).getAdminOrderList();
  }

  @Test
  @DisplayName("GET /admin/orders/{id}")
  void detail() throws Exception {
    User user = Fixtures.user("tester");
    Order order = Fixtures.order(user);

    OrderId orderId = order.id();

    mockMvc.perform(get("/admin/orders/" + orderId)
               .header("Authorization", "Bearer " + adminAccessToken))
           .andExpect(status().isOk());

    verify(getAdminOrderDetailService).getOrderDetail(orderId);
  }
}