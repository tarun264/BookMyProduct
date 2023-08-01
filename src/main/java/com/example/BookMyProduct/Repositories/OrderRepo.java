package com.example.BookMyProduct.Repositories;

import com.example.BookMyProduct.Models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity,Integer> {
}
