package com.example.BookMyProduct.Transformer;


import com.example.BookMyProduct.DTOs.Request.ProductRequestDTO;
import com.example.BookMyProduct.DTOs.Response.ProductResponseDTO;
import com.example.BookMyProduct.Enum.ProductStatus;
import com.example.BookMyProduct.Models.Customer;
import com.example.BookMyProduct.Models.Product;

public class ProductTransformer {

    public static Product productRequestDTOtoProduct(ProductRequestDTO productRequestDTO) {
        Product product = Product.builder()
                .productName(productRequestDTO.getProductName())
                .price(productRequestDTO.getPrice())
                .availableQuantity((productRequestDTO.getAvailableQuantity()))
                .productCategory(productRequestDTO.getProductCategory())
                .productStatus((ProductStatus.AVAILABLE))
                .build();
        return product;

    }
    public static ProductResponseDTO productToproductResponseDTO(Product product){
        return ProductResponseDTO.builder()
                .sellerName(product.getSeller().getName())
                .productName(product.getProductName())
                .availableQuantity(product.getAvailableQuantity())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .build();
    }

}
