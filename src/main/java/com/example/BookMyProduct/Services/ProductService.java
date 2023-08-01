package com.example.BookMyProduct.Services;

import com.example.BookMyProduct.DTOs.Request.ProductRequestDTO;
import com.example.BookMyProduct.DTOs.Response.ProductResponseDTO;
import com.example.BookMyProduct.Enum.ProductCategory;
import com.example.BookMyProduct.Exceptions.SellerNotPresentException;
import com.example.BookMyProduct.Models.Product;
import com.example.BookMyProduct.Models.Seller;
import com.example.BookMyProduct.Repositories.ProductRepo;
import com.example.BookMyProduct.Repositories.SellerRepo;
import com.example.BookMyProduct.Transformer.ProductTransformer;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    SellerRepo sellerRepo;
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        //check whether seller is present or not
        Seller seller =sellerRepo.findByEmailId(productRequestDTO.getSellerEmailId());
        if(seller==null){
             throw new SellerNotPresentException("Seller not Present");
        }
       //dto to entity
        Product product= ProductTransformer.productRequestDTOtoProduct(productRequestDTO);

        //setting seller
        product.setSeller(seller);
        seller.getProductList().add(product);

        //saving the parent
        Seller savedSeller= sellerRepo.save(seller);

        // to get the product from list
        List<Product> products=savedSeller.getProductList();
        Product latestProduct=products.get(products.size()-1);


        // prepare response DTO
       return ProductTransformer.productToproductResponseDTO(latestProduct);
    }

    public List<ProductResponseDTO> getProductByCategoryAndPriceGreaterThan(ProductCategory productCategory, int price) {
        // get list of people having category and price grater than using custom query
        List<Product> productList= productRepo.getProductByCategoryAndPriceGreaterThan(productCategory,price);

        //prepare list of dtos
        List<ProductResponseDTO> productResponseDtoList= new ArrayList<>();
        //converting each product of productlist to product response dto
        for(Product product: productList){
            productResponseDtoList.add(ProductTransformer.productToproductResponseDTO(product));
        }
        return productResponseDtoList;
    }

}
