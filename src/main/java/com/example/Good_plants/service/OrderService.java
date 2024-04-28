package com.example.Good_plants.service;

import com.example.Good_plants.dto.OrderDTO;
import com.example.Good_plants.entity.Order;
import com.example.Good_plants.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public Order create(OrderDTO dto) {
        Order order = Order.builder()
                .amount(dto.getAmount())
                .product_set(dto.getProduct_set())
                .build();
        return orderRepository.save(order);
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