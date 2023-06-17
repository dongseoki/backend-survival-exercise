package com.example.onlineshop.services;

import com.example.onlineshop.models.Cart;
import com.example.onlineshop.models.CartId;
import com.example.onlineshop.models.CartLineItemOption;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductId;
import com.example.onlineshop.models.UserId;
import com.example.onlineshop.repositories.CartRepository;
import com.example.onlineshop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
public class AddProductToCartService {
  private final CartRepository cartRepository;

  private final ProductRepository productRepository;

  public AddProductToCartService(CartRepository cartRepository,
                                 ProductRepository productRepository) {
    this.cartRepository = cartRepository;
    this.productRepository = productRepository;
  }

  public void addProductToCart(UserId userId, ProductId productId,
                               Set<CartLineItemOption> options, int quantity) {
    // 장바구니가 없으면 장바구니 객체를 만들고, 나중에 save를 해준다.
    Cart cart = cartRepository.findByUserId(userId)
                              .orElse(new Cart(CartId.generate(), userId));

    Product product = productRepository.findById(productId)
                                       .orElseThrow();

    cart.addProduct(product, options, quantity);

    cartRepository.save(cart);
  }
}
