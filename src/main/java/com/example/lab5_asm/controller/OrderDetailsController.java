package com.example.lab5_asm.controller;

import com.example.lab5_asm.model.Cart;
import com.example.lab5_asm.service.OrderDetailsService;
import com.example.lab5_asm.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    public String submitOrder() {
        Cart cart = sessionService.get("cart");  // Retrieve the cart from the session
        if (cart != null) {
            orderDetailsService.submitOrder(cart);  // Submit the order with the cart data
            System.out.println("Cart contains: " + cart.getProducts().size() + " products.");
        }
        return "order-confirmation-view";
    }
}
