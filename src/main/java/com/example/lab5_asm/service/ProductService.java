package com.example.lab5_asm.service;

import com.example.lab5_asm.model.Product;
import com.example.lab5_asm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products from the database
    public List<Product> getAllProducts() {
        return productRepository.findAll();  // Make sure this is correctly fetching data from your SQL Server database
    }


    // Get product by ID
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);  // Returns the product if present, or null if not found
    }
    public List<Product> getProductsBycategory_id(String category_id) {
        return productRepository.findByCategoryId(category_id);
    }



}
