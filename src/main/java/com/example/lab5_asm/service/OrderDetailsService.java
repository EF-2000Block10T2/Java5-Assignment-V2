package com.example.lab5_asm.service;

import com.example.lab5_asm.model.Cart;
import com.example.lab5_asm.model.OrderDetails;
import com.example.lab5_asm.model.Product;
import com.example.lab5_asm.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> submitOrder(Cart cart) {
        List<OrderDetails> orderDetailsList = cart.getProducts().stream()
                .map(product -> {
                    int price = product.getPrice();
                    return new OrderDetails(product, product.getName(), price, 1);
                })
                .collect(Collectors.toList());

        // Add logging to confirm list creation
        System.out.println("Created order details: " + orderDetailsList);

        // Save the order details in the database
        return orderDetailsRepository.saveAll(orderDetailsList); // Ensure this is saving correctly
    }

}
