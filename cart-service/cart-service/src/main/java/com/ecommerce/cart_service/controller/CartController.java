package com.ecommerce.cart_service.controller;

import com.ecommerce.cart_service.dto.CartItemDTO;
import com.ecommerce.cart_service.model.Cart;
import com.ecommerce.cart_service.service.CartService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addToCart(@Valid @RequestBody CartItemDTO dto, Authentication auth) {
        cartService.addItem(auth.getName(), dto);
    }

    @GetMapping
    public Cart getCart(Authentication auth) {
        return cartService.getCart(auth.getName());
    }

    @DeleteMapping("/clear")
    public void clearCart(Authentication auth) {
        cartService.clearCart(auth.getName());
    }

    @DeleteMapping("/remove/{productId}")
    public void removeItem(@PathVariable Long productId, Authentication auth) {
        cartService.removeItem(auth.getName(), productId);
    }
} 