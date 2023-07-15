package com.example.onlineshop.controllers;

import com.example.onlineshop.models.OrderId;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.services.CreateOrderService;
import com.example.onlineshop.services.GetOrderDetailService;
import com.example.onlineshop.services.GetOrderListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest extends ControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CreateOrderService createOrderService;

  @MockBean
  private GetOrderListService getOrderListService;

  @MockBean
  private GetOrderDetailService getOrderDetailService;


  @Test
  @DisplayName("POST /orders")
  void create() throws Exception {
    // mockMvc로 post 요청을 한다. 
    // 당연 토큰을 담음.
    //당연 application-json 으로.
    // 요청후 응답으로 201 created 가 오도록 조치하자!

    UserId userId = new UserId(USER_ID);
    String json = """
        {
            "receiver": {
                "name": "홍길동",
                "address1": "서울특별시 성동구 상원12길 34",
                "address2": "ㅇㅇㅇ호",
                "postalCode": "04790",
                "phoneNumber": "01012345678"
            },
            "payment": {
                "merchantId": "ORDER-20230101-00000001",
                "transactionId": "123456789012"
            }
        }
        """;
    mockMvc.perform(post("/orders")
        .header("Authorization", "Bearer " + userAccessToken)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)).andExpect(status().isCreated());

    verify(createOrderService).createOrder(eq(userId), any(), any());
  }

  @Test
  @DisplayName("GET /orders")
  void list() throws Exception {
    UserId userId = new UserId(USER_ID);

    mockMvc.perform(get("/orders")
               .header("Authorization", "Bearer " + userAccessToken))
           .andExpect(status().isOk());

    verify(getOrderListService).getOrderList(userId);
  }

  @Test
  @DisplayName("GET /orders/{id}")
  void detail() throws Exception {
    UserId userId = new UserId(USER_ID);
    String orderId = "ORDER-ID";

    mockMvc.perform(get("/orders/" + orderId)
               .header("Authorization", "Bearer " + userAccessToken))
           .andExpect(status().isOk());

    verify(getOrderDetailService)
        .getOrderDetail(new OrderId(orderId), userId);
  }
}