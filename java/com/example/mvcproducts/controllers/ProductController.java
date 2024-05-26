package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.mvcproducts.domain.ProductType;

import java.util.List;


@Controller
@Slf4j
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String seeProducts(Model model, Authentication authentication) {
    model.addAttribute("products", productService.findAll());
    User user = (User) authentication.getPrincipal();
    log.info(user.getUsername());
    return "products";
  }

  @PostMapping
  public String addProduct(Product product) {
    productService.save(product);
    return "redirect:/products";
  }

  @GetMapping("/skincare")
  public String seeSkincareProducts(Model model) {
    List<Product> skincareProducts = productService.findByType(ProductType.SKINCARE);
    model.addAttribute("products", skincareProducts);
    return "skincare";
  }

  @GetMapping("/makeup")
  public String seeMakeupProducts(Model model) {
    List<Product> makeupProducts = productService.findByType(ProductType.MAKEUP);
    model.addAttribute("products", makeupProducts);
    return "makeup";
  }
}
