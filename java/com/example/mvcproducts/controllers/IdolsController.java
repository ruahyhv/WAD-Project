package com.example.mvcproducts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IdolsController {

    @GetMapping("/idols") // Define the URL path for your idols page
    public String showIdolsPage() {
        return "idols"; // This corresponds to idols.html in your templates directory
    }
}
