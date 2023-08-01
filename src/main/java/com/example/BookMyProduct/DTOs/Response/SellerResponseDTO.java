package com.example.BookMyProduct.DTOs.Response;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerResponseDTO  {
    String name;
    String emailId;
}
