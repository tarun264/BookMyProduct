package com.example.BookMyProduct.Models;

import com.example.BookMyProduct.Enum.ProductCategory;
import com.example.BookMyProduct.Enum.ProductStatus;
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
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String productName;
    int price;
    int availableQuantity;

    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> itemList= new ArrayList<>();

}
