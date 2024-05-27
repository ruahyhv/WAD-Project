package com.example.mvcproducts.domain;

import jakarta.persistence.*;

import java.util.HashSet;

import java.util.Objects;
import java.util.Set;

@Entity
public class ProductOrder {
  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<OrderLineItem> orderLineItems = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductOrder that = (ProductOrder) o;
    return Objects.equals(orderLineItems, that.orderLineItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderLineItems);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<OrderLineItem> getOrderLineItems() {
    return orderLineItems;
  }

  public void setOrderLineItems(Set<OrderLineItem> items) {
    this.orderLineItems = items;
  }

  public ProductOrder() {
  }

  public ProductOrder(Long id, Set<OrderLineItem> orderLineItems) {
    this.id = id;
    this.orderLineItems = orderLineItems;
  }
}
