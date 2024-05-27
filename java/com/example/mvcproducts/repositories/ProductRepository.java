package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.Product;
//import com.example.mvcproducts.domain.ProductOrder;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
  @SuppressWarnings("null")
  List<Product> findAll();

//void save(ProductOrder productOrder);
}
