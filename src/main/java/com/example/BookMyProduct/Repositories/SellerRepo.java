package com.example.BookMyProduct.Repositories;

import com.example.BookMyProduct.Models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller,Integer> {
    public Seller findByEmailId(String email);
}
