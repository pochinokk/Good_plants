package com.example.Good_plants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/authentication")
    public String authentication(Model model) {
        return "authentication_page";
    }
}
