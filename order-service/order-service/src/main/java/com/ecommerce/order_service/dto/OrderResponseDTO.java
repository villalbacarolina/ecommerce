package com.ecommerce.order_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> items;
}
