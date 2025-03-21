package com.example.StoreManagement.service;

import com.example.StoreManagement.dto.RequestProductDto;
import com.example.StoreManagement.model.Product;
import com.example.StoreManagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product addProduct(RequestProductDto dto) {
        Product product = new Product();
        product.setPrice(dto.price());
        product.setName(dto.name());
        product.setDescription(dto.description());
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product changePrice(Long id, BigDecimal price) {
        Product product = repository.findById(id).orElseThrow();
        product.setPrice(price);
        return repository.save(product);
    }
}