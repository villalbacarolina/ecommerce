package com.ecommerce.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String author;
}

