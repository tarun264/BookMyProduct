package com.example.BookMyProduct.DTOs.Request;

import com.example.BookMyProduct.Enum.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardRequestDTO {
    String customerMobileNo;
    String cardNo;
    int cvv;
    Date validDate;
    CardType cardType;


}
