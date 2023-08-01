package com.example.BookMyProduct.Transformer;

import com.example.BookMyProduct.DTOs.Request.CustomerRequestDTO;
import com.example.BookMyProduct.DTOs.Response.CustomerResponseDTO;
import com.example.BookMyProduct.Models.Customer;
import lombok.Builder;
import lombok.experimental.UtilityClass;

//@UtilityClass
// we keep transformer as static so we would not require object to call function
public class CustomerTransformer {
    public static Customer customerRequestDTO(CustomerRequestDTO customerRequestDTO){

        //dto to entity
        Customer customer= Customer.builder()
                .name(customerRequestDTO.getName())
                .mobileNo(customerRequestDTO.getMobileNo())
                .gender(customerRequestDTO.getGender())
                .emailId(customerRequestDTO.getEmailId())
                .build();
        return customer;

    }
    public static CustomerResponseDTO customerResponseDTO(Customer customer){

        CustomerResponseDTO customerResponseDTO= CustomerResponseDTO.builder()
                .name(customer.getName())
                .mobileNo(customer.getMobileNo())
                .gender((customer.getGender()))
                .emailId(customer.getEmailId())
                .build();
        return customerResponseDTO;
    }
}
