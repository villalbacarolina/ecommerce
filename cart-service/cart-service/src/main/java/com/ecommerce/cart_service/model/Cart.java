package com.ecommerce.cart_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public Cart(String username) {
        this.username = username;
    }

    public void addItem(CartItem newItem) {
        for (CartItem item : items) 
            if (item.getProductId().equals(newItem.getProductId())) {
                item.updateQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        items.add(newItem);
        newItem.setCart(this);
    }
    
    public void removeItemByProductId(Long productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }

    public void clear() {
        items.clear();
    }
}
