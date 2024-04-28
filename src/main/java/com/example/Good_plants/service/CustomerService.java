package com.example.Good_plants.service;

import com.example.Good_plants.dto.CustomerDTO;
import com.example.Good_plants.entity.Customer;
import com.example.Good_plants.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    public Customer create(CustomerDTO dto) {
        Customer customer = Customer.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }
    public List<Customer> readAll() {
        return customerRepository.findAll();
    }
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}