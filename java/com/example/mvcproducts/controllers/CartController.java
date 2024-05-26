package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.*;
import com.example.mvcproducts.services.OrderService;
import com.example.mvcproducts.services.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {
    private final ProductService productService;
    private final OrderService orderService;

    public CartController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @ModelAttribute("cart")
    public Cart getCart() {
        return new Cart();
    }

    @GetMapping("/add")
    public String addToCart(@RequestParam("pid") Long productId, @ModelAttribute("cart") Cart cart) {
        Product product = productService.findById(productId);
        if (product != null) {
            cart.addProduct(product);
        }
        return "redirect:/products";
    }

    @GetMapping
    public String viewCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute("cart") Cart cart,
                           @RequestParam Map<String, String> quantities,
                           Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        ProductOrder order = new ProductOrder();

        cart.getProducts().forEach((product, oldQuantity) -> {
            int quantity = Integer.parseInt(quantities.getOrDefault("quantities[" + product.getId() + "]", "0"));
            if (quantity > 0) {
                OrderLineItem lineItem = new OrderLineItem();
                lineItem.setProduct(product);
                lineItem.setQty(quantity);
                order.getOrderLineItems().add(lineItem);
            }
        });

        orderService.saveOrder(order, user);
        cart.clear();
        return "redirect:/products";
    }
}
