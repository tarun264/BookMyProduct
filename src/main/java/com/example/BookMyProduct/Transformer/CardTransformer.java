package com.example.BookMyProduct.Transformer;

import com.example.BookMyProduct.DTOs.Request.CardRequestDTO;
import com.example.BookMyProduct.DTOs.Response.CardResponse;
import com.example.BookMyProduct.Models.Card;

public class CardTransformer {
    public static Card cardRequestDTOtoCard(CardRequestDTO cardRequestDTO){
        return Card.builder()
                .cardNo(cardRequestDTO.getCardNo())
                .cardType(cardRequestDTO.getCardType())
                .cvv(cardRequestDTO.getCvv())
                .validDate(cardRequestDTO.getValidDate())
                .cardType(cardRequestDTO.getCardType())
                .build();
    }
    public static CardResponse cardResponseDto(Card card){
        return CardResponse.builder()
                .customerName(card.getCustomer().getName())
                .validTill(card.getValidDate())
                .cardType(card.getCardType())
                .build();

    }

}
