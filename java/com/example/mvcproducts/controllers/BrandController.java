package com.example.mvcproducts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandController {

    @GetMapping("/brands") // Mapping for the brands page
    public String showBrandsPage() {
        return "brands"; // This will return the name of your HTML template for the brands page
    }
}
