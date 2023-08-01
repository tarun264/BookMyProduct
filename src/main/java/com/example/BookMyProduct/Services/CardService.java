package com.example.BookMyProduct.Services;

import com.example.BookMyProduct.DTOs.Request.CardRequestDTO;
import com.example.BookMyProduct.DTOs.Response.CardResponse;
import com.example.BookMyProduct.Exceptions.CustomerNotPresentException;
import com.example.BookMyProduct.Models.Card;
import com.example.BookMyProduct.Models.Customer;
import com.example.BookMyProduct.Repositories.CustomerRepo;
import com.example.BookMyProduct.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    CustomerRepo customerRepo;

    public CardResponse addCard(CardRequestDTO cardRequestDTO) {
        Customer customer= customerRepo.findByMobileNo(cardRequestDTO.getCustomerMobileNo());
        if(customer==null){
            throw new CustomerNotPresentException("Customer not available");
        }
        //create card entity
        Card card= CardTransformer.cardRequestDTOtoCard(cardRequestDTO);
        card.setCustomer(customer);
        customer.getCardsList().add(card);

        Customer savedCustomer= customerRepo.save(customer);
        List<Card> cards= savedCustomer.getCardsList();
        Card lastestCard= cards.get(cards.size()-1);

        //prepare entity to dto
        CardResponse cardResponse= CardTransformer.cardResponseDto(lastestCard);
        cardResponse.setCardNo(generateMaskedCardNo(lastestCard.getCardNo()));

        return cardResponse;

    }
    public String generateMaskedCardNo(String cardNo){
        int cardLength= cardNo.length();
        String maskedCard= "";

        for (int i = 0; i < cardLength; i++) {
            maskedCard+="X";
        }
        maskedCard+=cardNo.substring(cardLength-4);
        return maskedCard;
    }

}
