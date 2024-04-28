package com.example.Good_plants.controller;

import lombok.AllArgsConstructor;
import com.example.Good_plants.dto.ProductDTO;
import com.example.Good_plants.entity.Product;
import com.example.Good_plants.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class AdminController {

    private final ProductService productService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String addMenu(@RequestParam(value="ask_name", required = false) String ask_name, Model model) {
        List<Product> positions = productService.readAll();
        if (ask_name == null) {
            String answer = "Нет названия - нет ID";
            model.addAttribute("answer", answer);
        }
        else {
            long current_id = findIdByName(ask_name, positions);
            if (current_id == -1) {
                model.addAttribute("answer", "Такой позиции нет");
            }
            else {
                model.addAttribute("answer", "ID данной позиции: " + current_id);
            }
        }
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestParam String name, @RequestParam String price, Model model) {
        ProductDTO dto = new ProductDTO(name, price);
        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long delete_id, Model model) {
        productService.delete(delete_id);
    }

    public long findIdByName(String name, List<Product> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getName().equals(name))
                return list.get(i).getId();
        }
        return -1;
    }
}