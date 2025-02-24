package com.example.lab5_asm.controller;

import com.example.lab5_asm.model.OrderDetails;
import com.example.lab5_asm.model.Product;
import com.example.lab5_asm.repository.OrderDetailsRepository;
import com.example.lab5_asm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    // Show admin dashboard
    @GetMapping
    public String adminDashboard(Model model) {
        List<Product> products = productRepository.findAll();
        List<OrderDetails> orders = orderDetailsRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("orders", orders);
        return "admin-dashboard";
    }

    // Show product creation form
    @GetMapping("/product/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    // Save new product
    @PostMapping("/product/save")
    public String saveProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin";
    }

    // Show product update form
    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        return "product-form";
    }

    // Delete product
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin";
    }

    // View orders
    @GetMapping("/orders")
    public String viewOrders(Model model) {
        List<OrderDetails> orders = orderDetailsRepository.findAll();
        model.addAttribute("orders", orders);
        return "order-list";
    }
    @GetMapping("/mail")
    public String showMailForm() {
        return "admin-mail"; // This will load admin-mail.html
    }

}
