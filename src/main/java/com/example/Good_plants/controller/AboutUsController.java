package com.example.Good_plants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {
    @GetMapping("/about_us")
    public String about_us(Model model) {
        return "about_us_page";
    }
}
