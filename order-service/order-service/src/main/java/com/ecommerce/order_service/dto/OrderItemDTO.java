package com.ecommerce.order_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDTO {
	
    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;
}
