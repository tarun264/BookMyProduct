package com.example.BookMyProduct.Exceptions;

public class SellerNotPresentException extends RuntimeException{
    public SellerNotPresentException(String message){
        super(message);
    }
}
