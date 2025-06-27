package com.ecommerce.product_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must have at least 3 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 3, message = "Description must have at least 3 characters")
    private String description;

    @Min(value = 0, message = "Price must be zero or positive")
    private double price;
}
