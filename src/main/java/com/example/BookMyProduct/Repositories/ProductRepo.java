package com.example.BookMyProduct.Repositories;

import com.example.BookMyProduct.Enum.ProductCategory;
import com.example.BookMyProduct.Models.Product;
import com.example.BookMyProduct.Models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepo extends JpaRepository<Product,Integer> {

    //: means input price
    @Query("select p from Product p where p.productCategory = :productCategory and p.price >= :price")
    public List<Product> getProductByCategoryAndPriceGreaterThan(ProductCategory productCategory,int price);



}
