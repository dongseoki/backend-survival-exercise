package com.example.onlineshop.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_options")
public class ProductOption {
  @EmbeddedId
  private ProductOptionId id;

  @Column(name = "name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_option_id")
  @OrderBy("id")
  private List<ProductOptionItem> items = new ArrayList<>();

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public <E> ProductOption(ProductOptionId productOptionId, String name,
                           List<ProductOptionItem> productOptionItem) {
    this.id = productOptionId;
    this.name = name;
    this.items = productOptionItem;
  }

  public ProductOption() {
  }

  public ProductOptionId id() {
    return id;
  }

  public String name() {
    return name;
  }

  public List<ProductOptionItem> items() {
    return items;
  }

  public ProductOptionItem item(int optionItemIndex) {
    return items.get(optionItemIndex);
  }
}