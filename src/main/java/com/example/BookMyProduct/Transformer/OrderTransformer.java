package com.example.BookMyProduct.Transformer;

import com.example.BookMyProduct.DTOs.Request.OrderRequestDTO;
import com.example.BookMyProduct.DTOs.Response.ItemResponseDTO;
import com.example.BookMyProduct.DTOs.Response.OrderResponseDTO;
import com.example.BookMyProduct.Models.Item;
import com.example.BookMyProduct.Models.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {

    public static OrderResponseDTO ordertoResponseDTO(OrderEntity savedOrder){
        List<ItemResponseDTO> itemsList= new ArrayList<>();
        for(Item item: savedOrder.getItemList()){
            itemsList.add(ItemTransformer.itemToItemResponse(item));
        }

            return OrderResponseDTO.builder()
                .orderId(savedOrder.getOrderId())
                .orderDate(savedOrder.getOrderDate())
                .cardUsed((savedOrder.getCardUsed()))
                .orderTotal(savedOrder.getOrderTotal())
                .customerName(savedOrder.getCustomer().getName())
                .item(itemsList)
                    .build();

    }
}
