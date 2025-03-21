package com.example.StoreManagement.controller;

import com.example.StoreManagement.dto.RequestProductDto;
import com.example.StoreManagement.model.Product;
import com.example.StoreManagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody RequestProductDto dto) {
        return ResponseEntity.ok(service.addProduct(dto));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAll());
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<Product> changePrice(@PathVariable Long id, @RequestParam BigDecimal price) {
        return ResponseEntity.ok(service.changePrice(id, price));
    }
}