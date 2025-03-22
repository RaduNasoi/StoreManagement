package com.example.StoreManagement.controller;

import com.example.StoreManagement.dto.RequestProductDto;
import com.example.StoreManagement.model.Product;
import com.example.StoreManagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid RequestProductDto dto) {
        return ResponseEntity.ok(service.addProduct(dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        if (service.getProductById(id) != null) {
            return ResponseEntity.ok(service.getProductById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAll());
    }

    @PatchMapping("/{id}/price")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> changePrice(@PathVariable Long id, @RequestParam BigDecimal price) {
        return ResponseEntity.ok(service.changePrice(id, price));
    }

    @PatchMapping("/{id}/quantity")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> changeQuantity(@PathVariable Long id, @RequestParam int quantity) {
        return ResponseEntity.ok(service.changeQuantity(id, quantity));
    }
}