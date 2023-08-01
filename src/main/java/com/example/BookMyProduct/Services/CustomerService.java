package com.example.BookMyProduct.Services;

import com.example.BookMyProduct.DTOs.Request.CustomerRequestDTO;
import com.example.BookMyProduct.DTOs.Response.CustomerResponseDTO;
import com.example.BookMyProduct.Models.Cart;
import com.example.BookMyProduct.Models.Customer;
import com.example.BookMyProduct.Repositories.CustomerRepo;
import com.example.BookMyProduct.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) {

        // DTO to entity
        Customer customer= CustomerTransformer.customerRequestDTO(customerRequestDTO);

// assigning the cart to the customer
        Cart cart= new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);

        //saving the customer
        Customer savedCustomer = customerRepo.save(customer);

// Entity to DTO
        return CustomerTransformer.customerResponseDTO(savedCustomer);

    }




}
