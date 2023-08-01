package com.example.BookMyProduct.Repositories;

import com.example.BookMyProduct.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,Integer> {
}
