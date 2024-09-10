package com.example.computerstore.models;

import java.util.List;

public class CheckoutRequest {
    private List<CartItem> cart;
    private String total;

   

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
 
class CartItem {
    private String id;
    private String name;
    private int quantity;
    private double price;

    
}
