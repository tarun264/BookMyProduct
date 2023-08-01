package com.example.BookMyProduct.Models;

import com.example.BookMyProduct.Enum.Gender;
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
@Table(name = "customer")
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true)
    String mobileNo;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(unique = true)
    String emailId;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Card> cardsList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();

}