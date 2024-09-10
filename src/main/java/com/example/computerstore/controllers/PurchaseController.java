package com.example.computerstore.controllers;

import com.example.computerstore.models.Order;
import com.example.computerstore.models.User;
import com.example.computerstore.repository.OrderRepository;
import com.example.computerstore.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public String purchaseItem(@RequestBody User user) {
        
        Order order = new Order();
        order.setUser(user);  
        order.setOrderDate(new Date());  
        order.setStatus("Pending");  

       
        orderRepository.save(order);

      
        String purchaseMessage = "User " + user.getUsername() + " purchased an item. Order ID: " + order.getId();
        messageProducer.sendMessage(purchaseMessage);

        return "Purchase successful. A confirmation email will be sent shortly.";
    }
}
