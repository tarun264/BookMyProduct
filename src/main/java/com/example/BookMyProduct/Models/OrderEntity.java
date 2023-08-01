package com.example.BookMyProduct.Models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderId; //UUID
    @CreationTimestamp
    Date orderDate;

    String cardUsed;
    int orderTotal;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Customer customer;
}
