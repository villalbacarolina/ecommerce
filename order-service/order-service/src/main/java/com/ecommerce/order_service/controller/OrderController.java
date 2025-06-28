package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.dto.OrderItemDTO;
import com.ecommerce.order_service.dto.OrderResponseDTO;
import com.ecommerce.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public OrderResponseDTO placeOrder(@Valid @RequestBody List<OrderItemDTO> items, Authentication auth) {
        return orderService.placeOrder(auth.getName(), items);
    }

    @GetMapping
    public List<OrderResponseDTO> getOrders(Authentication auth) {
        return orderService.getOrders(auth.getName());
    }
}
