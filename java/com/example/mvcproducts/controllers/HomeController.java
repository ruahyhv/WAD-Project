package com.example.mvcproducts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
  // // Login form
  // @RequestMapping("/")
  // @GetMapping
  // public String home(){
  //   return "home";
  // }
  
  // @RequestMapping("/login.html")
  // public String login() {
  //   return "login.html";
  // }
  @GetMapping("/login")
    public String login() {
        return "login"; // This should match the name of your login.html template
    }

    @GetMapping("/")
    public String home() {
        return "home"; // This should match the name of your home.html template
    }
}
