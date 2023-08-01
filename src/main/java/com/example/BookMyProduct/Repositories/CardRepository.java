package com.example.BookMyProduct.Repositories;

import com.example.BookMyProduct.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
    public Card findByCardNo(String cardNo);
}
