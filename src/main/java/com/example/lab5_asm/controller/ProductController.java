package com.example.lab5_asm.controller;

import com.example.lab5_asm.model.Product;
import com.example.lab5_asm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        System.out.println("Fetched Products: " + products);
        model.addAttribute("products", products);
        return "product-list";
    }

}
