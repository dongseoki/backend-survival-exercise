package com.example.onlineshop.dtos;

import com.example.onlineshop.models.Image;

public record ImageDto(
    String url
) {
  public static ImageDto of(String url) {
    return new ImageDto(url);
  }

  public static ImageDto of(Image image) {
    return new ImageDto(image.url());
  }
}
