package com.example.BookMyProduct.DTOs.Request;

import com.example.BookMyProduct.Controllers.ProductController;
import com.example.BookMyProduct.Enum.ProductCategory;
import com.example.BookMyProduct.Enum.ProductStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDTO {
    //to set which sellers will have these product
    String sellerEmailId;
    String productName;
    int price;
    int availableQuantity;
    ProductCategory productCategory;
    ProductStatus productStatus;

}
