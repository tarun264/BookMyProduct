package com.example.BookMyProduct.Services;

import com.example.BookMyProduct.DTOs.Request.SellerRequestDTO;
import com.example.BookMyProduct.DTOs.Response.SellerResponseDTO;
import com.example.BookMyProduct.Models.Seller;
import com.example.BookMyProduct.Repositories.SellerRepo;
import com.example.BookMyProduct.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepo sellerRepo;
    public SellerResponseDTO addSeller(SellerRequestDTO sellerRequestDTO) {
        //Dto to entity
        Seller seller= SellerTransformer.sellerRequestDTOtoSeller(sellerRequestDTO);

       //save the entity
        Seller savedSeller= sellerRepo.save(seller);

        //entity to dto
        SellerResponseDTO sellerResponseDTO=SellerTransformer.sellerToSellerResponseDTO(savedSeller);
        return sellerResponseDTO;


    }
}
