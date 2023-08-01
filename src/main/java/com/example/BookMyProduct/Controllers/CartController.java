package com.example.BookMyProduct.Controllers;

import com.example.BookMyProduct.DTOs.Request.CheckoutCartRequestDto;
import com.example.BookMyProduct.DTOs.Request.ItemRequestDTO;
import com.example.BookMyProduct.DTOs.Response.CartResponseDTO;
import com.example.BookMyProduct.DTOs.Response.OrderResponseDTO;
import com.example.BookMyProduct.Models.Item;
import com.example.BookMyProduct.Services.CartService;
import com.example.BookMyProduct.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add_to_cart")
    public ResponseEntity addToCart(@RequestBody ItemRequestDTO itemRequestDto) {
        try {
            Item item = itemService.createItem(itemRequestDto);
            //saving the cart
            CartResponseDTO cartResponseDTO = cartService.addItemtoCart(itemRequestDto, item);
            return new ResponseEntity(cartResponseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/checkout")
        public ResponseEntity checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto){
        try {
            OrderResponseDTO orderResponseDTO= cartService.checkout(checkoutCartRequestDto);
            return new ResponseEntity(orderResponseDTO,HttpStatus.ACCEPTED);
        } catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
