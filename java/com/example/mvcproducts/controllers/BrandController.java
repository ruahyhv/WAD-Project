package com.example.mvcproducts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BrandController {

    @GetMapping("/brands")
    public String displayBrands(Model model) {
        List<String> brands = new ArrayList<>();
        brands.add("Brand1");
        brands.add("Brand2");
        brands.add("Brand3");
        // Add more brands as needed

        model.addAttribute("brands", brands);
        return "brands";
    }
}
