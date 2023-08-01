package com.example.BookMyProduct.Exceptions;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException(String message){
        super(message);
    }
}
