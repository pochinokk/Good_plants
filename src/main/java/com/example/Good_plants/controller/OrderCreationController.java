package com.example.Good_plants.controller;


import com.example.Good_plants.config.CustomerDetails;
import com.example.Good_plants.entity.Product;
import com.example.Good_plants.service.OrderService;
import com.example.Good_plants.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('USER')")
@AllArgsConstructor
public class OrderCreationController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/order_creation")
    public String positions(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        session.setAttribute("username", username);
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);

        Iterable<Product> positions = productService.readAll();
        model.addAttribute("positions", positions);
        return "order_creation_page";
    }


    @PostMapping("/save_order")
    public String createOrder(@RequestParam String amount, @RequestParam String str) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomerDetails) {
            CustomerDetails customerDetails = (CustomerDetails) authentication.getPrincipal();
            Long customer_id = customerDetails.getId();
            orderService.create(amount, str, customer_id);
            System.out.println(amount);
            System.out.println(str);
            return "redirect:/order_creation?message";
        }
        return "redirect:/authentication";
    }

}


