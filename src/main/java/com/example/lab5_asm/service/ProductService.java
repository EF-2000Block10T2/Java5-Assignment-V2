package com.example.lab5_asm.service;

import com.example.lab5_asm.model.Product;
import com.example.lab5_asm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products with pagination
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Get products by category with pagination
    public Page<Product> getProductsByCategoryId(String category_id, Pageable pageable) {
        return productRepository.findByCategoryId(category_id, pageable);
    }
}

