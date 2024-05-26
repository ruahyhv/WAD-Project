package com.example.mvcproducts.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        products.putIfAbsent(product, 0);
        products.put(product, products.get(product) + 1);
    }

    public void removeProduct(Product product) {
        if (products.containsKey(product) && products.get(product) > 1) {
            products.put(product, products.get(product) - 1);
        } else {
            products.remove(product);
        }
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public void clear() {
        products.clear();
    }
}
