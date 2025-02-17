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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String getProducts(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              Model model) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Product> productPage = productRepository.findAll(pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "product-list"; // Ensure this matches your Thymeleaf template
    }

    @GetMapping("/category/{categoryId}")
    public String getProductsByCategory(@PathVariable String categoryId,
                                        @RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        Model model) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("categoryId", categoryId);

        return "filtered-products"; // Ensure this matches your Thymeleaf template
    }
}
