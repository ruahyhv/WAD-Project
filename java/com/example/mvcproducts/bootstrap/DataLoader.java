package com.example.mvcproducts.bootstrap;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.ProductType;
import com.example.mvcproducts.domain.Role;
import com.example.mvcproducts.domain.User;
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
            new Product("Lipozom Oil Ampoule", ProductType.SKINCARE, "Nice car",60, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\sk3.jpg" ),
            new Product("Centella Asiatica Face Mask", ProductType.SKINCARE, "Lenovo",12, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\sk4.jpg" ),
            new Product("Green Tangerine C Dark Spot Care Serum", ProductType.SKINCARE, "Office",40, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\sk5.jpg" ),
            new Product("A'PIEU Icing Sweet Bar Sheet Mask", ProductType.SKINCARE, "win",10, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\sk6.jpg" ),
            new Product("Anua Birch 70% Moisture Boosting Serum", ProductType.SKINCARE, "Sweet",80, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\sk7.jpg" ),
            new Product("Vegan Rice Milk Toner", ProductType.SKINCARE, "Sweet",50, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\sk8.jpg" ),
            new Product("UNLEASHIA Don't Touch Glass Pink Cushion", ProductType.MAKEUP, "Sweet",50, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\m1.jpg" ),
            new Product("LANEIGE Glowy Makeup Serum", ProductType.MAKEUP, "Sweet",110, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\m5.jpg" ),
            new Product("Glacier Vegan Lip Balm", ProductType.MAKEUP, "Sweet",70, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\m2.jpg" ),
            new Product("CLIO Pro Eye Palette", ProductType.MAKEUP, "Sweet",130, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\m6.jpg" ),
            new Product("LANEIGE Ultimistic Whipping Tint", ProductType.MAKEUP, "Sweet",100, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\m7.jpg" ),
            new Product("CLIO Kill Cover Mesh Glow Cushion Set", ProductType.MAKEUP, "Sweet",65, "C:\\Users\\Ruxandra\\Desktop\\lab06+07_wad\\springSecurity-final\\src\\main\\resources\\static\\images\\m9.jpg" )
    );
    productService.saveAll(productList);

    PasswordEncoder bcrypt = new BCryptPasswordEncoder();
    User user1=new User("user1",bcrypt.encode("user1"));
    user1.getRoles().add(Role.ROLE_USER);
    User user2=new User("user2",bcrypt.encode("user2"));
    user2.getRoles().add(Role.ROLE_ADMIN);
    userService.save(user1);
    userService.save(user2);
  }
}
