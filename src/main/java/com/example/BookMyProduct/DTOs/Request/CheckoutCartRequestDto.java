package com.example.BookMyProduct.DTOs.Request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutCartRequestDto {
    String customerEmail;
    String cardNo;
    int cvv;
}
