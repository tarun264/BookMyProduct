package com.example.BookMyProduct.Transformer;

import com.example.BookMyProduct.DTOs.Request.SellerRequestDTO;
import com.example.BookMyProduct.DTOs.Response.SellerResponseDTO;
import com.example.BookMyProduct.Models.Seller;

public class SellerTransformer {
    public static Seller sellerRequestDTOtoSeller(SellerRequestDTO sellerRequestDTO){
        Seller seller=Seller.builder()
                .name(sellerRequestDTO.getName())
                .emailId(sellerRequestDTO.getEmailId())
                .panNo(sellerRequestDTO.getPanNo())
                .build();
        return seller;
    }
    public static SellerResponseDTO sellerToSellerResponseDTO(Seller seller){
        return SellerResponseDTO.builder()
                .name(seller.getName())
                .emailId(seller.getEmailId())
                .build();
    }
}
