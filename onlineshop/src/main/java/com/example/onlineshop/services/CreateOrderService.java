package com.example.onlineshop.services;

import com.example.onlineshop.models.Cart;
import com.example.onlineshop.models.CartLineItem;
import com.example.onlineshop.models.Order;
import com.example.onlineshop.models.OrderId;
import com.example.onlineshop.models.OrderLineItem;
import com.example.onlineshop.models.OrderLineItemId;
import com.example.onlineshop.models.OrderOption;
import com.example.onlineshop.models.OrderOptionId;
import com.example.onlineshop.models.OrderStatus;
import com.example.onlineshop.models.Payment;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductOption;
import com.example.onlineshop.models.ProductOptionId;
import com.example.onlineshop.models.ProductOptionItem;
import com.example.onlineshop.models.ProductOptionItemId;
import com.example.onlineshop.models.Receiver;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.repositories.CartRepository;
import com.example.onlineshop.repositories.OrderRepository;
import com.example.onlineshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional
public class CreateOrderService {
  private final ProductRepository productRepository;
  private final CartRepository cartRepository;
  private final OrderRepository orderRepository;

  public CreateOrderService(ProductRepository productRepository,
                            CartRepository cartRepository,
                            OrderRepository orderRepository) {
    this.productRepository = productRepository;
    this.cartRepository = cartRepository;
    this.orderRepository = orderRepository;
  }

  private static OrderOption createOrderOption(
      CartLineItem cartLineItem, Product product,
      ProductOptionId optionId) {
    ProductOptionItemId itemId = cartLineItem.optionItemId(optionId);

    ProductOption productOption = product.optionById(optionId);
    ProductOptionItem productOptionItem = productOption.itemById(itemId);

    return new OrderOption(
        OrderOptionId.generate(),
        productOption,
        productOptionItem
    );
  }

  public Order createOrder(
      UserId userId, Receiver receiver, Payment payment) {
    Cart cart = cartRepository.findByUserId(userId)
                              .orElseThrow();

    List<OrderLineItem> lineItems = IntStream
        .range(0, cart.lineItemSize())
        .mapToObj(cart::lineItem)
        .map(this::createOrderLineItem)
        .toList();

    OrderId orderId = OrderId.generate();

    Order order = new Order(orderId, userId, lineItems, receiver, payment,
        OrderStatus.PAID);

    orderRepository.save(order);

    return order;
  }

  private OrderLineItem createOrderLineItem(CartLineItem cartLineItem) {
    Product product = productRepository.findById(cartLineItem.productId())
                                       .orElseThrow();

    List<OrderOption> options = cartLineItem.optionIds().stream()
                                            .map(optionId ->
                                                createOrderOption(cartLineItem, product, optionId))
                                            .toList();

    return new OrderLineItem(
        OrderLineItemId.generate(),
        product,
        options,
        cartLineItem.quantity()
    );
  }
}