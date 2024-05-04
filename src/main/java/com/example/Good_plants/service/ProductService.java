package com.example.Good_plants.service;

import com.example.Good_plants.entity.Product;
import com.example.Good_plants.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
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
    public Iterable<Product> readAllSortedByName() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
    public Iterable<Product> readAllSortedByPriceAscending() {
        List<Product> positions = productRepository.findAll();
        positions.sort(Comparator.comparingDouble(product -> Double.parseDouble(product.getPrice())));
        return positions;
    }
    public Iterable<Product> readAllSortedByPriceDescending() {
        List<Product> positions = productRepository.findAll();
        Comparator<Product> priceComparator = Comparator.comparingDouble(product -> Double.
                parseDouble(product.getPrice()));
        Collections.sort(positions, priceComparator.reversed());
        return positions;
    }
    public List<Product> readAll() {
        return productRepository.findAll();
    }
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
