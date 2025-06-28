package com.ecommerce.cart_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;
}
