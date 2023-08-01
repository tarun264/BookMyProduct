package com.example.BookMyProduct.DTOs.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    String orderId;
    Date orderDate;
    String cardUsed;
    int orderTotal;
    String customerName;
    List<ItemResponseDTO> item;


}
