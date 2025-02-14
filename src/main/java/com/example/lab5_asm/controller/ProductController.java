package com.example.lab5_asm.controller;

import com.example.lab5_asm.model.Product;
import com.example.lab5_asm.repository.ProductRepository;
import com.example.lab5_asm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequestMapping("/products")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product-list"; // Return the main products page
    }

    // Method to display filtered products based on category
    @GetMapping("/category/{categoryId}")
    public String getProductsByCategory(@PathVariable String categoryId, Model model) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        model.addAttribute("products", products);
        return "filtered-products"; // Return the filtered products page
    }




}
