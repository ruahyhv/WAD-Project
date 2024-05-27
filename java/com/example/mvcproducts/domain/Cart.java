package com.example.mvcproducts.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderLineItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        for (OrderLineItem item : items) {
            if (item.getProduct().equals(product)) {
                item.setQty(item.getQty() + 1);
                return;
            }
        }
        items.add(new OrderLineItem(null, product, 1));
    }

    public void removeProduct(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    public void clear() {
        items.clear();
    }

    public List<OrderLineItem> getItems() {
        return items;
    }

    public void clearItems() {
        this.items.clear();
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQty()).sum();
    }
}
