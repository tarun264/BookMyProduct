package com.example.BookMyProduct.DTOs.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {
    int cartTotal;
    String customerName;
     // cannot show the list of items since it will breach the security directly so we will make use of
    //item response dto
     List<ItemResponseDTO> items;
}
