package com.example.onlineshop.utils;

import com.example.onlineshop.models.CartLineItemOption;
import com.example.onlineshop.models.Product;

public class TestUtils {
  public static CartLineItemOption createCartLineItemOption(
      Product product, int optionIndex, int optionItemIndex) {
    return new CartLineItemOption(
        product.option(optionIndex).id(),
        product.option(optionIndex).item(optionItemIndex).id());
  }
}
