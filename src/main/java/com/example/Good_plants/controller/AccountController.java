package com.example.Good_plants.controller;

import com.example.Good_plants.entity.Order;
import com.example.Good_plants.service.CustomerService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class AccountController {
    @Autowired
    private OrderService orderService;
    private CustomerService customerService;

    @GetMapping("/account")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String personalAccount(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        session.setAttribute("username", username);
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"));
        Long id = customerService.getIDByName(username);
        if (isAdmin) {
            return "admin_page";
        } else {
            Iterable<Order> orders = orderService.readAllByUserId(id);
            model.addAttribute("orders", orders);
            return "account_page";
        }
    }

    @PostMapping("/save_admin_order")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String save_admin_order(@RequestParam("username") String username,
                                   @RequestParam("str") String str,
                                   Model model, RedirectAttributes redirectAttributes) {
        String amount = orderService.getOrderAmount(str);
        Long customer_id = customerService.getIDByName(username);
        str = str.replace("_", " ");
        System.out.println(str);
        System.out.println(amount);
        System.out.println(customer_id);
        if (!amount.equals("-1") && !amount.equals("0") && customer_id > 0) {
            orderService.create(amount, str, customer_id);
            System.out.println("OK");
            redirectAttributes.addFlashAttribute("mes", "Заказ успешно создан");
            return "redirect:/account";
        } else if (amount.equals("-1") && customer_id < 0){
            redirectAttributes.addFlashAttribute("er", "Ошибка в обоих полях");
            return "redirect:/account";
        } else if (amount.equals("-1")) {
            redirectAttributes.addFlashAttribute("er", "Ошибка в наборе растений");
            return "redirect:/account";
        }
        else {
            redirectAttributes.addFlashAttribute("er", "Пользователь не существует");
            return "redirect:/account";
        }
    }

    @PostMapping("/delete_order")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String delete_order(@RequestParam("id") Long order_id,
                               Model model, RedirectAttributes redirectAttributes) {

        if (orderService.exists(order_id)) {
            orderService.delete(order_id);
            redirectAttributes.addFlashAttribute("mes", "Заказ успешно удален");
            return "redirect:/account";
        } else {
            redirectAttributes.addFlashAttribute("er", "Такого заказа нет");
            return "redirect:/account";
        }
    }

    @GetMapping("/find_order_by_ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String find_order_by_ID(@RequestParam("id") Long order_id,
                                   Model model, RedirectAttributes redirectAttributes) {
        Order order  = orderService.getOrderByID(order_id);
        if (order != null) {
            System.out.println("OK");
            redirectAttributes.addFlashAttribute("order", order);
            return "redirect:/account";
        } else {
            redirectAttributes.addFlashAttribute("er", "Такого заказа нет");
            return "redirect:/account";
        }
    }

    @GetMapping("/find_customer_orders")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String find_customer_orders(@RequestParam("username") String username,
                                       Model model, RedirectAttributes redirectAttributes) {
        if (customerService.exists(username) && !username.equals("anonymousUser")) {
            Long id = customerService.getIDByName(username);
            Iterable<Order> orders = orderService.readAllByUserId(id);
            redirectAttributes.addFlashAttribute("orders", orders);
            return "redirect:/account";
        }
        redirectAttributes.addFlashAttribute("er", "Такого пользователя нет");
        return "redirect:/account";
    }
}




