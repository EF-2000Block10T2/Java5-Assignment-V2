package com.example.lab5_asm.controller;
import com.example.lab5_asm.model.Cart;
import com.example.lab5_asm.model.Product;
import com.example.lab5_asm.service.OrderDetailsService;
import com.example.lab5_asm.service.ProductService;
import com.example.lab5_asm.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SessionService sessionService;

    // Initialize a cart in the session
    private Cart getCartFromSession() {
        Cart cart = sessionService.get("cart");  // Retrieve the cart from session using SessionService
        if (cart == null) {
            cart = new Cart();
            sessionService.set("cart", cart);  // Set the cart in session if it's null
        }
        return cart;
    }

    // Add product to the cart
    @PostMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable Long id) {
        Product product = productService.getProductById(id);  // Fetch product by ID
        Cart cart = getCartFromSession();  // Get the cart from session
        cart.addProduct(product);  // Add product to cart
        return "redirect:/products";  // Redirect back to products list
    }

    // Remove product from the cart
    @PostMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable Long id) {
        Cart cart = getCartFromSession();
        cart.removeProduct(id);  // Remove product from cart
        return "redirect:/cart";  // Redirect to the cart page
    }

    // View the cart
    @GetMapping("/cart")
    public String viewCart(Model model) {
        Cart cart = getCartFromSession();  // Get the cart from session
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart-view";  // Return the cart view template
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        Cart cart = getCartFromSession();  // Get the cart from session
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "checkout-view";  // Return the checkout view template
    }
}

