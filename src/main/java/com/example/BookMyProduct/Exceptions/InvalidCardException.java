package com.example.BookMyProduct.Exceptions;

public class InvalidCardException extends  RuntimeException{
    public InvalidCardException(String message){
        super(message);
    }
}
