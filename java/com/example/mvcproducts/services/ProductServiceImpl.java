package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.repositories.OrderRepository;
//import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  //added
  private final OrderRepository orderRepository;  // Use the correct repository for ProductOrder
  public ProductServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
}

@Override
public void saveAll(Iterable<Product> products) {
    productRepository.saveAll(products);
}

@Override
public List<Product> findAll() {
    return productRepository.findAll();
}

@Override
public void save(Product product) {
    productRepository.save(product);
}

@Override
public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
}

@Override
public void save(ProductOrder productOrder) {
    orderRepository.save(productOrder);  // Correctly use the OrderRepository
}
}
