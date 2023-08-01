package com.example.BookMyProduct.DTOs.Response;

import com.example.BookMyProduct.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDTO {
    int quantityAdded;
    String itemName;
    int itemPrice;
    ProductCategory productCategory;
}
