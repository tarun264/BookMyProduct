package com.example.BookMyProduct.DTOs.Request;

import com.example.BookMyProduct.Enum.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
    String name;
    String mobileNo;
    Gender gender;
    String emailId;
}
