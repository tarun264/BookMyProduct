package com.example.BookMyProduct.Controllers;

import com.example.BookMyProduct.DTOs.Request.ProductRequestDTO;
import com.example.BookMyProduct.DTOs.Response.ProductResponseDTO;
import com.example.BookMyProduct.Enum.ProductCategory;
import com.example.BookMyProduct.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add_product")
    public ResponseEntity addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        try {
            ProductResponseDTO responseDTO = productService.addProduct(productRequestDTO);
            return new ResponseEntity(responseDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_by_category_and_price_greater_than")
    public ResponseEntity getProductByCategoryAndPriceGreaterThan(@RequestParam("category") ProductCategory productCategory, @RequestParam("price") int price){
        List<ProductResponseDTO> productResponseDTOlist=productService.getProductByCategoryAndPriceGreaterThan(productCategory,price);
        return new ResponseEntity(productResponseDTOlist,HttpStatus.FOUND);
    }
}
