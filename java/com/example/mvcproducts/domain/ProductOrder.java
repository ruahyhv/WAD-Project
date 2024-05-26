package com.example.mvcproducts.domain;

import lombok.Data;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ProductOrder {
  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<OrderLineItem> orderLineItems = new HashSet<>();

  @ManyToOne
  private User user; // Add this field to associate the order with a user
}
