package com.example.BookMyProduct.DTOs.Response;

import com.example.BookMyProduct.Enum.ProductCategory;
import com.example.BookMyProduct.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    String sellerName;
    String productName;
    int price;
    int availableQuantity;
    ProductCategory productCategory;
    ProductStatus productStatus;
}
