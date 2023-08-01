package com.example.BookMyProduct.Services;

import com.example.BookMyProduct.DTOs.Request.CheckoutCartRequestDto;
import com.example.BookMyProduct.DTOs.Request.ItemRequestDTO;
import com.example.BookMyProduct.DTOs.Response.CartResponseDTO;
import com.example.BookMyProduct.DTOs.Response.OrderResponseDTO;
import com.example.BookMyProduct.Exceptions.CustomerNotPresentException;
import com.example.BookMyProduct.Exceptions.EmptyCartException;
import com.example.BookMyProduct.Exceptions.InvalidCardException;
import com.example.BookMyProduct.Models.*;
import com.example.BookMyProduct.Repositories.*;
import com.example.BookMyProduct.Transformer.CartTransformer;
import com.example.BookMyProduct.Transformer.OrderTransformer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ItemRepo itemRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderService orderService;


    public CartResponseDTO addItemtoCart(ItemRequestDTO itemRequestDto, Item item) {
        // get cart of the customer and add the item
        Customer customer=customerRepo.findByEmailId(itemRequestDto.getCustomerEmail());
        Product product=productRepo.findById(itemRequestDto.getProductId()).get();

        //update the status of the cartTotal
        Cart cart=customer.getCart();
        cart.setCartTotal(cart.getCartTotal()+ product.getPrice()* itemRequestDto.getRequiredQuantity());

        //setting the list of items
        item.setCart(cart);
        item.setProduct(product);
        Item savedItem= itemRepo.save(item); //to avoid duplicacy
        //adding item to product
        cart.getItemList().add(savedItem);
        product.getItemList().add(savedItem);

        Cart savedCart=cartRepo.save(cart);
        productRepo.save(product);

        //prepare cart response dto
        return CartTransformer.cartToCartResponseDTO(savedCart);


    }

    public OrderResponseDTO checkout(CheckoutCartRequestDto checkoutCartRequestDto) {

        Customer customer= customerRepo.findByEmailId(checkoutCartRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotPresentException("Customer doesn't exist");
        }
        Card card= cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        Date currentDate= new Date();
        if(card==null || card.getCvv()!= checkoutCartRequestDto.getCvv() || currentDate.after(card.getValidDate())){
            throw new InvalidCardException("Invalid Card");
        }

        //check if cart is not empty since we cannot checkout empty cart
        Cart cart= customer.getCart();
        if(cart.getItemList().size()==0){
            throw new EmptyCartException("Cart is Empty");
        }
        //ready to place the order using single point of responsibility
        // by using placeorder funct usinf method overloading
        OrderEntity order= orderService.placeOrder(cart,card);
        //reset the cart after ordering
        resetCart(cart);

        OrderEntity savedOrder= orderRepo.save(order);

        //Prepare response dto
        return OrderTransformer.ordertoResponseDTO(savedOrder);

    }


    public void resetCart(Cart cart){
        cart.setCartTotal(0);
        //now these items dont belong to any cart
        for(Item item: cart.getItemList()){
            item.setCart(null);
        }
        cart.setItemList(new ArrayList<>());
    }
}
