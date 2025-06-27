package com.ecommerce.product_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private String author;
    
    public Product(String name, String description, double price, String author) {
        if (price < 0) throw new IllegalArgumentException("Price can't be negative");
        this.name = name;
        this.description = description;
        this.price = price;
        this.author = author;
    }
    
    public void updatePrice(double newPrice, String requester) {
        verifyOwner(requester);
        if (newPrice < 0) throw new IllegalArgumentException("Price can't be negative");
        this.price = newPrice;
    }

    public void updateName(String newName, String requester) {
        verifyOwner(requester);
        this.name = newName;
    }

    public void updateDescription(String newDescription, String requester) {
        verifyOwner(requester);
        this.description = newDescription;
    }

    private void verifyOwner(String requester) {
        if (!requester.equals(this.author))
            throw new SecurityException("Only the author can modify this product");
    }
    
}
