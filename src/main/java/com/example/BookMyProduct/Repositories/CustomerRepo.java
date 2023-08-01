package com.example.BookMyProduct.Repositories;

import com.example.BookMyProduct.DTOs.Request.CustomerRequestDTO;
import com.example.BookMyProduct.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    public Customer findByMobileNo(String mobileNo);
    public Customer findByEmailId(String emailId);



}
