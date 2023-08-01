package com.example.BookMyProduct.Exceptions;

public class InsufficientQuantityAvailable extends RuntimeException{
    public InsufficientQuantityAvailable(String message){
        super(message);
    }
}
