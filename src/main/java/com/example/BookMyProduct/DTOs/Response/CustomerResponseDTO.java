package com.example.BookMyProduct.DTOs.Response;

import com.example.BookMyProduct.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    String name;
    String mobileNo;
    Gender gender;
    String emailId;
}
