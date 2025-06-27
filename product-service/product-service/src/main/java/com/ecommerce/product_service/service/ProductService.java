package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.*;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

//import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public ProductResponseDTO create(ProductCreateDTO dto, String author) {
        Product product = new Product(dto.getName(), dto.getDescription(), dto.getPrice(), author);
        repo.save(product);
        return toResponse(product);
    }

    public ProductResponseDTO update(Long id, ProductUpdateDTO dto, String requester) {
        Product product = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        product.updateName(dto.getName(), requester);
        product.updateDescription(dto.getDescription(), requester);
        product.updatePrice(dto.getPrice(), requester);

        repo.save(product);
        return toResponse(product);
    }

    public ProductResponseDTO getById(Long id) {
        Product product = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        return toResponse(product);
    }

    private ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getAuthor()
        );
    }
}
