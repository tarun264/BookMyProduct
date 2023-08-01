package com.example.BookMyProduct.DTOs.Response;

import com.example.BookMyProduct.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {
    String customerName;
    String cardNo; // masked card no
    Date validTill;
    CardType cardType;
}
