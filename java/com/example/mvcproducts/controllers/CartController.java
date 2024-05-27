package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Cart;
import com.example.mvcproducts.domain.OrderLineItem;
import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.services.OrderService;
import com.example.mvcproducts.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@SessionAttributes("cart")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/add")
    public String addToCart(@RequestParam Long pid, @ModelAttribute("cart") Cart cart, RedirectAttributes redirectAttributes) {
        productService.findById(pid).ifPresentOrElse(
            product -> {
                cart.addProduct(product);
                redirectAttributes.addFlashAttribute("success", "Product added to cart successfully!");
            },
            () -> redirectAttributes.addFlashAttribute("error", "Product not found!")
        );
        return "redirect:/products";
    }

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @GetMapping
    public String seeCart(Model model, @ModelAttribute("cart") Cart cart) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute("cart") Cart cart, @RequestParam List<Integer> quantities, Model model) {
        try {
            Set<OrderLineItem> items = new HashSet<>();
            for (int i = 0; i < cart.getItems().size(); i++) {
                OrderLineItem item = cart.getItems().get(i);
                item.setQty(quantities.get(i));
                items.add(item);
            }

            ProductOrder productOrder = new ProductOrder();
            productOrder.setOrderLineItems(items);

            orderService.save(productOrder);
            cart.clearItems();

            model.addAttribute("successMessage", "Order placed successfully!");
            return "cart";

        } catch (Exception e) {
            System.err.println("Error during checkout: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("errorMessage", "Failed to place the order.");
            return "cart";
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateCartItem(@RequestBody Map<String, Object> payload, @ModelAttribute("cart") Cart cart) {
        Long itemId = Long.valueOf(payload.get("itemId").toString());
        int quantity = Integer.parseInt(payload.get("quantity").toString());

        for (OrderLineItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(itemId)) {
                item.setQty(quantity);
                break;
            }
        }

        double totalPrice = cart.getTotalPrice();
        Map<Long, Double> itemPrices = new HashMap<>();
        for (OrderLineItem item : cart.getItems()) {
            itemPrices.put(item.getProduct().getId(), item.getProduct().getPrice() * item.getQty());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalPrice", totalPrice);
        response.put("itemPrices", itemPrices);
        return response;
    }
}
