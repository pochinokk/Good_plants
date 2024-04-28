package com.example.Good_plants.controller;

import com.example.Good_plants.dto.CustomerDTO;
import com.example.Good_plants.entity.Customer;
import com.example.Good_plants.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        return "registration_page";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
//        CustomerDTO customerDTO = new CustomerDTO(username, password, "USER");
        customerService.create(username, password, "USER");
        return "redirect:/authentication/message";
    }
}