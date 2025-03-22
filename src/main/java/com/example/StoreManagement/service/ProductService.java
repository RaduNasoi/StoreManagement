package com.example.StoreManagement.service;

import com.example.StoreManagement.dto.RequestProductDto;
import com.example.StoreManagement.exceptions.ProductNotFoundException;
import com.example.StoreManagement.mapper.ProductMapper;
import com.example.StoreManagement.model.Product;
import com.example.StoreManagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.example.StoreManagement.util.StoreManagementConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public Product addProduct(RequestProductDto dto) {
        log.info(ADDING_PRODUCT, dto.name());
        Product product = productMapper.toProduct(dto);
        log.debug(SAVED_PRODUCT, product);
        return repository.save(product);
    }

    public Product getProductById(Long id) {
        log.info(GET_PRODUCT_BY_ID, id);
        return repository.findById(id).orElseThrow(
                () -> {
                    log.error(PRODUCT_NOT_FOUND, id);
                    return new ProductNotFoundException(PRODUCT_NOT_FOUND_ERROR_MESSAGE);
                });
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product changePrice(Long id, BigDecimal price) {
        log.info(CHANGING_PRICE, id, price);
        Product product = getProductById(id);
        product.setPrice(price);
        return repository.save(product);
    }

    public Product changeQuantity(Long id, Integer quantity) {
        log.info(CHANGING_QUANTITY, id, quantity);
        Product product = getProductById(id);
        product.setQuantity(quantity);
        return repository.save(product);
    }
}