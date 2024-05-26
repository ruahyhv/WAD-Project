package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Cart;
import com.example.mvcproducts.domain.ProductType;

import com.example.mvcproducts.domain.Product;

import java.util.List;

public interface ProductService {
  void saveAll(Iterable<Product> products);
  List<Product> findAll();
  void save(Product product);
  Product findById(Long id);
  List<Product> findByType(ProductType type); // Add this method
}