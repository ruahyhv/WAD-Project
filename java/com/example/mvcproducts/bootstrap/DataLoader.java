package com.example.mvcproducts.bootstrap;

import com.example.mvcproducts.domain.*;
import com.example.mvcproducts.repositories.BrandRepository;
import com.example.mvcproducts.services.BrandService;
import com.example.mvcproducts.services.ProductService;
import com.example.mvcproducts.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DataLoader implements CommandLineRunner {
  private final ProductService productService;
  private final UserService userService;

  public DataLoader(ProductService productService, UserService userService) {
    this.productService = productService;
    this.userService = userService;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Product> productList = List.of(
            new Product("Propolis Honey Overnight Mask", ProductType.SKINCARE, "A nourishing overnight mask", 30),
            new Product("Some By Mi Bye Blackhead", ProductType.SKINCARE, "Blackhead remover", 15),
            new Product("Lipozom Oil Ampoule 50ml", ProductType.SKINCARE, "Hydrating ampoule", 25),
            new Product("Eyeshadow Palette", ProductType.MAKEUP, "Various shades", 30),
            new Product("Lipstick", ProductType.MAKEUP, "Long-lasting", 15),
            new Product("Foundation", ProductType.MAKEUP, "Full coverage", 25)
    );
    productService.saveAll(productList);
    PasswordEncoder bcrypt = new BCryptPasswordEncoder();
    User user1 = new User("user1", bcrypt.encode("user1"));
    user1.getRoles().add(Role.ROLE_USER);
    User user2 = new User("user2", bcrypt.encode("user2"));
    user2.getRoles().add(Role.ROLE_ADMIN);
    userService.save(user1);
    userService.save(user2);
  }
}
