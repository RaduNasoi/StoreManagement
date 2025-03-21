package com.example.StoreManagement.dto;

import java.math.BigDecimal;

public record ResponseProductDto(
    Long id,
    String name,
    BigDecimal price,
    int quantity,
    String description,
    String category
) {}
