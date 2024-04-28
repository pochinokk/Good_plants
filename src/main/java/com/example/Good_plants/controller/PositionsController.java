package com.example.Good_plants.controller;


import com.example.Good_plants.entity.Product;
import com.example.Good_plants.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PositionsController {
    @Autowired
    private ProductService productService;

    @GetMapping("/order_creation")
    public String positions(Model model) {
        Iterable<Product> positions = productService.readAll();
        model.addAttribute("positions", positions);
        return "order_creation_page";
    }
    @PostMapping("/save_order")
    public String createOrder(@RequestParam String amount, @RequestParam String str) {
        System.out.println(amount);
        System.out.println(str);
        return "redirect:/order_creation";
    }

}


