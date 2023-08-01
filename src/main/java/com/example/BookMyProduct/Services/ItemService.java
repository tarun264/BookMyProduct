package com.example.BookMyProduct.Services;

import com.example.BookMyProduct.DTOs.Request.ItemRequestDTO;
import com.example.BookMyProduct.Enum.ProductCategory;
import com.example.BookMyProduct.Exceptions.CustomerNotPresentException;
import com.example.BookMyProduct.Exceptions.InsufficientQuantityAvailable;
import com.example.BookMyProduct.Exceptions.ProductNotFoundException;
import com.example.BookMyProduct.Models.Customer;
import com.example.BookMyProduct.Models.Item;
import com.example.BookMyProduct.Models.Product;
import com.example.BookMyProduct.Repositories.CustomerRepo;
import com.example.BookMyProduct.Repositories.ProductRepo;
import com.example.BookMyProduct.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ProductRepo productRepo;

    public Item createItem(ItemRequestDTO itemRequestDto) {
        Customer customer = customerRepo.findByEmailId(itemRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotPresentException("Customer Not Present");
        }

        Optional<Product> productOptional = productRepo.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product Not Available");
        }
        Product product = productOptional.get();

        //check for required quantity
        if(product.getAvailableQuantity()<itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityAvailable("Sorry!, required quantity not available");
        }
        // if all condition satisfy means item can be created
        Item item = ItemTransformer.ItemRequestDTOtoItem(itemRequestDto.getRequiredQuantity());
        return item;

    }
}
