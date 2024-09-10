package com.example.computerstore.controllers;

import com.example.computerstore.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.computerstore.models.CheckoutRequest;;

@RestController
public class CheckoutController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/api/checkout")
    public String checkout(@RequestBody CheckoutRequest request) {
        String message = "Cart: " + request.getCart() + ", Total: " + request.getTotal();
        messageProducer.sendMessage(message);
        return "Checkout message sent";
    }
}
