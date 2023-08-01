package com.example.BookMyProduct.Exceptions;

public class CustomerNotPresentException extends RuntimeException{
    public CustomerNotPresentException(String message){
        super(message);
    }
}
