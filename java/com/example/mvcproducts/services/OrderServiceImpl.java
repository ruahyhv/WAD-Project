package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void saveOrder(ProductOrder order, User user) {
    order.setUser(user); // Assuming you have a User field in the ProductOrder class
    orderRepository.save(order);
  }
}
