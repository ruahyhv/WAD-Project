package com.example.mvcproducts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotController {

    @GetMapping("/forgot")
    public String showForgotPage() {
        return "forgot";
    }
}
