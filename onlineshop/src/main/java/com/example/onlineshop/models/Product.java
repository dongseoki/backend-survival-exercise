package com.example.onlineshop.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name = "products")
public class Product {
  @EmbeddedId
  private ProductId id;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "category_id"))
  private CategoryId categoryId;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_id")
  @OrderBy("id")
  private List<Image> images = new ArrayList<>();

  @Column(name = "name")
  private String name;

  @Embedded
  @AttributeOverride(name = "amount", column = @Column(name = "price"))
  private Money price;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_id")
  @OrderBy("id")
  private List<ProductOption> options = new ArrayList<>();

  @Column(name = "description")
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public Product() {
  }


  public CategoryId categoryId() {
    return categoryId;
  }

  public String id() {
    return id.toString();
  }

  public List<Image> images() {
    return new ArrayList<>(images);
  }

  public String name() {
    return name;
  }

  public Money price() {
    return price;
  }

  public String description() {
    return description;
  }


  public int optionSize() {
    return options.size();
  }

  public ProductOption option(int i) {
    return options.get(i);
  }
}