package com.example.BookMyProduct.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cartTotal;

    @OneToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();
}
