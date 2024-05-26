package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.Product;
import org.springframework.data.repository.CrudRepository;
import com.example.mvcproducts.domain.ProductType;


import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findAll();
    public List<Product> findByType(ProductType type);
}
