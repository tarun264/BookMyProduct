package com.example.BookMyProduct.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seller")
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true,nullable = false)
    String emailId;

    @Column(unique = true,nullable = false)
    String panNo;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    List<Product> productList= new ArrayList<>();
}
