package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.ProductService;
import com.example.mvcproducts.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final StorageService storageService;
    //private final Path rootLocation = Paths.get("G:\AN3\sem2\WAD -lab\lab7\springSecurity-final\src\main\resources\static\images");

    public ProductController(ProductService productService, StorageService storageService) {
        this.productService = productService;
        this.storageService = storageService;
    }

    @GetMapping
    public String seeProducts(Model model, Authentication authentication) {
        model.addAttribute("products", productService.findAll());
        User user = (User) authentication.getPrincipal();
        log.info(user.getUsername());
        return "products";
    }

    @PostMapping
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile image) {
        if (!image.isEmpty()) {
            storageService.save(image);
            product.setImage(image.getOriginalFilename());
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{pid}/image")
    public ResponseEntity<Resource> getImage(@PathVariable("pid") Long pid) {
        Optional<Product> optionalProduct = productService.findById(pid);
        if (!optionalProduct.isPresent() || optionalProduct.get().getImage() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product product = optionalProduct.get();
        Resource file = storageService.load(product.getImage());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }



}
