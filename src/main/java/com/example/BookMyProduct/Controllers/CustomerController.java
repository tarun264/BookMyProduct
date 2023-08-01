package com.example.BookMyProduct.Controllers;

import com.example.BookMyProduct.DTOs.Request.CustomerRequestDTO;
import com.example.BookMyProduct.DTOs.Response.CustomerResponseDTO;
import com.example.BookMyProduct.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add_customer")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
      CustomerResponseDTO responseDTO= customerService.addCustomer(customerRequestDTO);
      return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

}
