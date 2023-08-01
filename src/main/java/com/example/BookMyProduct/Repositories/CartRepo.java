package com.example.BookMyProduct.Repositories;

import com.example.BookMyProduct.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Integer> {
}
