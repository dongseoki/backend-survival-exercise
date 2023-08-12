package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.dtos.AdminOrderDetailDto;
import com.example.onlineshop.dtos.AdminOrderListDto;
import com.example.onlineshop.dtos.AdminUpdateOrderDto;
import com.example.onlineshop.models.OrderId;
import com.example.onlineshop.models.OrderStatus;
import com.example.onlineshop.security.AdminRequired;
import com.example.onlineshop.services.GetAdminOrderDetailService;
import com.example.onlineshop.services.GetOrderListService;
import com.example.onlineshop.services.UpdateOrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AdminRequired
@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {
  private final GetOrderListService getOrderListService;
  private final UpdateOrderService updateOrderService;

  private final GetAdminOrderDetailService getAdminOrderDetailService;

  public AdminOrderController(GetOrderListService getOrderListService,
                              UpdateOrderService updateOrderService,
                              GetAdminOrderDetailService getAdminOrderDetailService) {
    this.getOrderListService = getOrderListService;
    this.updateOrderService = updateOrderService;
    this.getAdminOrderDetailService = getAdminOrderDetailService;
  }

  @GetMapping
  public AdminOrderListDto list() {
    return getOrderListService.getAdminOrderList();
  }

  @GetMapping("/{id}")
  public AdminOrderDetailDto detail(@PathVariable String id) {
    OrderId orderId = new OrderId(id);
    return getAdminOrderDetailService.getOrderDetail(orderId);
  }

  @PatchMapping("/{id}")
  public String update(
      @PathVariable String id,
      @Valid @RequestBody AdminUpdateOrderDto orderDto) {
    OrderId orderId = new OrderId(id);
    OrderStatus status = OrderStatus.of(orderDto.status());
    updateOrderService.updateOrderStatus(orderId, status);
    return "Updated";
  }
}