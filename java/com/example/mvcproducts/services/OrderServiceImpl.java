package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
  @SuppressWarnings("unused")
  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }
  @Autowired
    private OrderRepository productOrderRepository;

    public void save(ProductOrder productOrder) {
        productOrderRepository.save(productOrder);
    }
}
