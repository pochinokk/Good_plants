package com.example.Good_plants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlantCareController {
    @GetMapping("/plant_care")
    public String plant_care(Model model) {
        return "plant_care_page";
    }
}

