package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.domain.User;

public interface OrderService {
    void saveOrder(ProductOrder order, User user);
}
