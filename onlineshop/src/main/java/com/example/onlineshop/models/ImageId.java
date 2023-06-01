package com.example.onlineshop.models;

public class ImageId extends EntityId {
  private ImageId() {
    super();
  }

  public ImageId(String value) {
    super(value);
  }

  public static ImageId generate() {
    return new ImageId(newTsid());
  }
}
