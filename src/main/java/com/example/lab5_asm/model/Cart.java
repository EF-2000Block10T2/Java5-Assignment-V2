package com.example.lab5_asm.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart {
    private List<Product> products = new ArrayList<>();

    // Add product to the cart
    public void addProduct(Product product) {
        products.add(product);
    }

    // Remove product from the cart
    public void removeProduct(Long productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }

    // Get total price of all products in the cart (formatted into VND)
    public String getTotalPrice() {
        // Sum the total price as int (removes decimals)
        int totalPrice = products.stream()
                .mapToInt(product -> product.getPrice())  // Get price as int
                .sum();

        // Format total price into VND (Vietnamese Dong)
        return formatPrice(totalPrice);
    }

    // Get all products in the cart
    public List<Product> getProducts() {
        return products;
    }

    // Format the price into VND format (Currency format)
    private String formatPrice(int price) {
        // Using Locale for Vietnam to get VND currency format
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return numberFormat.format(price);
    }
}
