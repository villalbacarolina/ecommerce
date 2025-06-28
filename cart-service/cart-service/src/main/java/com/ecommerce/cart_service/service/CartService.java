package com.ecommerce.cart_service.service;

import com.ecommerce.cart_service.dto.CartItemDTO;
import com.ecommerce.cart_service.model.Cart;
import com.ecommerce.cart_service.model.CartItem;
import com.ecommerce.cart_service.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void addItem(String username, CartItemDTO dto) {
        Cart cart = cartRepository.findByUsername(username).orElseGet(() -> new Cart(username));
        
        cart.addItem(new CartItem(dto.getProductId(), dto.getQuantity()));
        cartRepository.save(cart);
    }

    public Cart getCart(String username) {
        return cartRepository.findByUsername(username).orElseGet(() -> new Cart(username));
    }

    @Transactional
    public void clearCart(String username) {
        cartRepository.findByUsername(username).ifPresent(Cart::clear);
    }
    
    @Transactional
    public void removeItem(String username, Long productId) {
        cartRepository.findByUsername(username).ifPresent(cart -> {
            cart.removeItemByProductId(productId);
            cartRepository.save(cart);
        });
    }
}
