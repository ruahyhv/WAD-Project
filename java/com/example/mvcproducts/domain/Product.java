package com.example.mvcproducts.domain;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
//  @Enumerated(EnumType.ORDINAL)
  private ProductType type;
  private String description;
  private double price;
  private String image;

  public Product(String name, ProductType type, String description, double price, String image) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.price = price;
    this.image= image;
  }

  public Product() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Double.compare(price, product.price) == 0 && Objects.equals(name, product.name) && type == product.type && Objects.equals(description, product.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, description, price);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductType getType() {
    return type;
  }

  public void setType(ProductType type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
      this.image = image;
  }
}
