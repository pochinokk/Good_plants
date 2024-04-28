package com.example.Good_plants.service;

import com.example.Good_plants.dto.ProductDTO;
import com.example.Good_plants.entity.Product;
import com.example.Good_plants.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Product create(String name, String price) {
        Product product = Product.builder()
                .name(name)
                .price(price)
                .build();
        return productRepository.save(product);
    }
    public List<Product> readAll() {
        return productRepository.findAll();
    }
    public Product update(Product product) {
        return productRepository.save(product);
    }
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
