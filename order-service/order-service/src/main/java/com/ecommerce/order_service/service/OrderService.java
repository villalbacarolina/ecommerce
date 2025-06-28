package com.ecommerce.order_service.service;

import com.ecommerce.order_service.dto.OrderItemDTO;
import com.ecommerce.order_service.dto.OrderResponseDTO;
import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.model.OrderItem;
import com.ecommerce.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderResponseDTO placeOrder(String username, List<OrderItemDTO> itemsDTOs) {
        Order order = new Order(username);

        for (OrderItemDTO itemDto : itemsDTOs) {
            OrderItem item = new OrderItem(itemDto.getProductId(), itemDto.getQuantity());
            order.addItem(item);
        }

        Order saved = orderRepository.save(order);

        return toResponseDTO(saved);
    }

    public List<OrderResponseDTO> getOrders(String username) {
        return orderRepository.findByUsername(username)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private OrderResponseDTO toResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());

        List<OrderItemDTO> items = order.getItems().stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setProductId(item.getProductId());
            itemDTO.setQuantity(item.getQuantity());
            return itemDTO;
        }).collect(Collectors.toList());

        dto.setItems(items);
        return dto;
    }
}
