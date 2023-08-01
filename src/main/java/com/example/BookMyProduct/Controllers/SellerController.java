package com.example.BookMyProduct.Controllers;

import com.example.BookMyProduct.DTOs.Request.SellerRequestDTO;
import com.example.BookMyProduct.DTOs.Response.SellerResponseDTO;
import com.example.BookMyProduct.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add_seller")
    public ResponseEntity addSeller(@RequestBody SellerRequestDTO sellerRequestDTO){
        SellerResponseDTO response= sellerService.addSeller(sellerRequestDTO);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
