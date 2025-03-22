package com.example.StoreManagement.mapper;

import com.example.StoreManagement.dto.RequestProductDto;
import com.example.StoreManagement.dto.ResponseProductDto;
import com.example.StoreManagement.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ResponseProductDto toResponseProductDto(Product entity) {
        return new ResponseProductDto(entity.getId(), entity.getName(), entity.getPrice(), entity.getQuantity());
    }

    public List<ResponseProductDto> toListOfResponseProductDto(List<Product> entity) {
        return entity.stream().map(this::toResponseProductDto).collect(Collectors.toList());
    }

    public Product toProduct(RequestProductDto dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());
        return product;
    }
}