package com.example.orders.exception;

public class OrderNotFound extends RuntimeException{
    String message;

    public OrderNotFound(String message){
        super(message);
        this.message = message;
    }
}
