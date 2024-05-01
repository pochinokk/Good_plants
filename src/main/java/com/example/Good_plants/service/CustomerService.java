package com.example.Good_plants.service;


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
    public void create(String username, String password, String roles, String tel, String address) {
        Customer customer = Customer.builder()
                .name(username)
                .password(password)
                .roles(roles)
                .tel(tel)
                .address(address)
                .build();
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }
    public boolean exists(String username) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer cust : customers){
            if (cust.getName().equals(username)){
                return true;
            }
        }
        return false;
    }
    public Long getIDByName(String username) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer cust : customers){
            if (cust.getName().equals(username)){
                return cust.getId();
            }
        }
        return (long) -1;
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