package com.example.StoreManagement.controller;

import com.example.StoreManagement.dto.RequestProductDto;
import com.example.StoreManagement.dto.ResponseProductDto;
import com.example.StoreManagement.mapper.ProductMapper;
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
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseProductDto> addProduct(@RequestBody @Valid RequestProductDto dto) {
        return ResponseEntity.ok(productMapper.toResponseProductDto(productService.addProduct(dto)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ResponseProductDto> getProductById(@PathVariable Long id) {
        if (productService.getProductById(id) != null) {
            return ResponseEntity.ok(productMapper.toResponseProductDto(productService.getProductById(id)));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ResponseProductDto>> getAllProducts() {
        return ResponseEntity.ok(productMapper.toListOfResponseProductDto(productService.getAll()));
    }

    @PatchMapping("/{id}/price")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseProductDto> changePrice(@PathVariable Long id, @RequestParam BigDecimal price) {
        Product product = productService.changePrice(id, price);
        return ResponseEntity.ok(productMapper.toResponseProductDto(product));
    }

    @PatchMapping("/{id}/quantity")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseProductDto> changeQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        Product product = productService.changeQuantity(id, quantity);
        return ResponseEntity.ok(productMapper.toResponseProductDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}