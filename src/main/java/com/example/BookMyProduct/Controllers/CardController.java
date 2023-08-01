package com.example.BookMyProduct.Controllers;

import com.example.BookMyProduct.DTOs.Request.CardRequestDTO;
import com.example.BookMyProduct.DTOs.Response.CardResponse;
import com.example.BookMyProduct.Exceptions.CustomerNotPresentException;
import com.example.BookMyProduct.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add_card")
    public ResponseEntity addCard(@RequestBody CardRequestDTO cardRequestDTO){
        try {
            CardResponse cardResponse = cardService.addCard(cardRequestDTO);
            return new ResponseEntity<>(cardResponse, HttpStatus.CREATED);
        }catch (CustomerNotPresentException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
