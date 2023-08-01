package com.example.BookMyProduct.DTOs.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    String customerEmailId;  // customer who is ordering the item
    int productId;  // which product customer want to have
    String cardUsed;
    int cvv;
    int requiredQuantity;
}
