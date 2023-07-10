package com.example.onlineshop.controllers;

import com.example.onlineshop.dtos.OrderRequestDto;
import com.example.onlineshop.models.Address;
import com.example.onlineshop.models.AuthUser;
import com.example.onlineshop.models.Payment;
import com.example.onlineshop.models.PhoneNumber;
import com.example.onlineshop.models.PostalCode;
import com.example.onlineshop.models.Receiver;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.security.UserRequired;
import com.example.onlineshop.services.CreateOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@UserRequired
@RestController
@RequestMapping("/orders")
public class OrderController {
  private final CreateOrderService createOrderService;


  public OrderController(CreateOrderService createOrderService) {
    this.createOrderService = createOrderService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String create(Authentication authentication,
                       @Valid @RequestBody OrderRequestDto orderRequestDto) {
    AuthUser authUser = (AuthUser) authentication.getPrincipal();
    UserId userId = new UserId(authUser.id());

    OrderRequestDto.ReceiverDto receiverDto = orderRequestDto.receiver();
    OrderRequestDto.PaymentDto paymentDto = orderRequestDto.payment();

    Receiver receiver = new Receiver(
        receiverDto.name(),
        new Address(
            receiverDto.address1(),
            receiverDto.address2(),
            new PostalCode(receiverDto.postalCode())
        ),
        new PhoneNumber(receiverDto.phoneNumber())
    );

    Payment payment = new Payment(
        paymentDto.merchantId(),
        paymentDto.transactionId()
    );

    createOrderService.createOrder(userId, receiver, payment);
    return "Created";
  }
}
