package com.example.BookMyProduct.Transformer;

import com.example.BookMyProduct.DTOs.Request.ItemRequestDTO;
import com.example.BookMyProduct.DTOs.Response.ItemResponseDTO;
import com.example.BookMyProduct.Models.Cart;
import com.example.BookMyProduct.Models.Item;

public class ItemTransformer {

    public static Item ItemRequestDTOtoItem(int requiredQuantity) {
        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();

    }

    public static ItemResponseDTO itemToItemResponse(Item item) {
        return ItemResponseDTO.builder()
                .itemPrice(item.getProduct().getPrice())
                .itemName(item.getProduct().getProductName())
                .quantityAdded(item.getRequiredQuantity())
                .productCategory(item.getProduct().getProductCategory())
                .build();
    }
}

