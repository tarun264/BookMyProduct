package com.example.BookMyProduct.Transformer;

import com.example.BookMyProduct.DTOs.Response.CartResponseDTO;
import com.example.BookMyProduct.DTOs.Response.ItemResponseDTO;
import com.example.BookMyProduct.Models.Cart;
import com.example.BookMyProduct.Models.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDTO cartToCartResponseDTO(Cart cart){
        List<ItemResponseDTO> itemList= new ArrayList<>();
        for(Item item: cart.getItemList()){
            itemList.add(ItemTransformer.itemToItemResponse(item));
        }

        return CartResponseDTO.builder()
                .customerName(cart.getCustomer().getName())
                .cartTotal(cart.getCartTotal())
                .items(itemList)
                .build();

}
}
