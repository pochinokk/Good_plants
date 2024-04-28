package com.example.Good_plants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }
    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }
}
