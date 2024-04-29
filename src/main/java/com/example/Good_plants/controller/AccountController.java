package com.example.Good_plants.controller;

import com.example.Good_plants.entity.Order;
import com.example.Good_plants.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAuthority('USER')")
@AllArgsConstructor
public class AccountController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/account")
    public String personalAccount(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        session.setAttribute("username", username);
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);

        Iterable<Order> orders = orderService.readAll();
        model.addAttribute("orders", orders);
        return "account_page";
    }


}

