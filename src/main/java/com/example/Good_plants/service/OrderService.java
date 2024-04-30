package com.example.Good_plants.service;

import com.example.Good_plants.dto.OrderDTO;
import com.example.Good_plants.entity.Customer;
import com.example.Good_plants.entity.Order;
import com.example.Good_plants.entity.Product;
import com.example.Good_plants.repository.CustomerRepository;
import com.example.Good_plants.repository.OrderRepository;
import com.example.Good_plants.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    public Order create(String amount, String product_set, Long customer_id) {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Order order = Order.builder()
                .customer(customer)
                .amount(amount)
                .product_set(product_set)
                .build();
        return orderRepository.save(order);
    }
    public String getOrderAmount(String str) {
        int amount = 0;
        List<List<String>> order_list = new ArrayList<>();
        String[] a = str.split(",\\s*");
        for (String pos : a) {
            String[] b = pos.split("_");
            if (b.length == 2) {
                List<String> pair = new ArrayList<>();
                pair.add(b[0]);
                if (b[1].length() >= 3) {
                    b[1] = b[1].substring(0, b[1].length() - 3);
                }
                pair.add(b[1]);
                order_list.add(pair);
            }
        }
        List<Product> positions = productRepository.findAll();
        System.out.println(positions);
        for (List<String> pair : order_list) {
            String productName = pair.get(0);
            int quantity = Integer.parseInt(pair.get(1));
            boolean found = false;

            for (Product product : positions) {
                if (product.getName().equals(productName)) {
                    int price = Integer.parseInt(product.getPrice());
                    amount += price * quantity;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return "-1";
            }
        }
        return Integer.toString(amount);
    }
    public List<Order> readAll() {
        return orderRepository.findAll();
    }
    public Order update(Order order) {
        return orderRepository.save(order);
    }
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}