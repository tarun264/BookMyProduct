package com.example.BookMyProduct.Services;

import com.example.BookMyProduct.DTOs.Request.OrderRequestDTO;
import com.example.BookMyProduct.DTOs.Response.OrderResponseDTO;
import com.example.BookMyProduct.Enum.ProductStatus;
import com.example.BookMyProduct.Exceptions.CustomerNotPresentException;
import com.example.BookMyProduct.Exceptions.InsufficientQuantityAvailable;
import com.example.BookMyProduct.Exceptions.InvalidCardException;
import com.example.BookMyProduct.Exceptions.ProductNotFoundException;
import com.example.BookMyProduct.Models.*;
import com.example.BookMyProduct.Repositories.CardRepository;
import com.example.BookMyProduct.Repositories.CustomerRepo;
import com.example.BookMyProduct.Repositories.OrderRepo;
import com.example.BookMyProduct.Repositories.ProductRepo;
import com.example.BookMyProduct.Transformer.ItemTransformer;
import com.example.BookMyProduct.Transformer.OrderTransformer;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    JavaMailSender javaMailSender;
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {

        //check for customer
        Customer customer=customerRepo.findByEmailId(orderRequestDTO.getCustomerEmailId());
        if(customer==null){
            throw new CustomerNotPresentException("Customer doesn't exist");
        }

        //check for product
        Optional<Product> productOptional = productRepo.findById(orderRequestDTO.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product Not Available");
        }

        // check for card
        Card card = cardRepository.findByCardNo(orderRequestDTO.getCardUsed());
        Date todayDate= new Date();
        if(card==null || card.getCvv()!=orderRequestDTO.getCvv() || todayDate.after(card.getValidDate())){
            throw new InvalidCardException("Card is Invalid");
        }

        //Required Quantity
        Product product= productOptional.get();
        if(product.getAvailableQuantity()< orderRequestDTO.getRequiredQuantity()){
            throw new InsufficientQuantityAvailable("Insufficient quantity");
        }

        // since we are placing the order, we will decrease the quantity
        int newQuantity=product.getAvailableQuantity()-orderRequestDTO.getRequiredQuantity();
        product.setAvailableQuantity(newQuantity);
        if(newQuantity==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

        //Prepare order entity
        OrderEntity orderEntity= new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.generateMaskedCardNo(orderRequestDTO.getCardUsed()));
        orderEntity.setOrderTotal(orderRequestDTO.getRequiredQuantity()*product.getPrice());

        //creating the item to set the itemlist
        Item item= ItemTransformer.ItemRequestDTOtoItem(orderRequestDTO.getRequiredQuantity());
        item.setOrderEntity(orderEntity);
        item.setProduct(product);

        //adding the list of items in order
        orderEntity.getItemList().add(item);
        orderEntity.setCustomer(customer);


        //now saving all the entities
        OrderEntity savedOrder= orderRepo.save(orderEntity); // save order and item since its the parent

        //setting product entity parameters
        product.getItemList().add(savedOrder.getItemList().get(0));
        //setting customer entity parameters
        customer.getOrders().add(savedOrder);

        // save the parent only when it does not exist in data base otherwise will give infinite recurrsion
        //productRepo.save(product);
        //customerRepo.save(customer);

        //send email
        sendEmail(savedOrder);

        //prepare response dto
        return OrderTransformer.ordertoResponseDTO(savedOrder);



    }

    public OrderEntity placeOrder(Cart cart, Card card) {
        OrderEntity order =new OrderEntity();
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        order.setCardUsed(cardService.generateMaskedCardNo(card.getCardNo()));

        int orderTotal=0;

        //check for items and setting the parameters of item
        for(Item item:cart.getItemList()){
            //check for product in itemlist
            Product product=item.getProduct();
            if(product.getAvailableQuantity()< item.getRequiredQuantity()){
                throw new InsufficientQuantityAvailable("Insufficient quantity available for "+product.getProductName());
            }
            int newQuantity= product.getAvailableQuantity()- item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            if(newQuantity==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
            orderTotal+=product.getPrice()*item.getRequiredQuantity();
            item.setOrderEntity(order);
        }

        order.setOrderTotal(orderTotal);
        order.setItemList(cart.getItemList());
        order.setCustomer(cart.getCustomer());

        return order;
    }
    public void sendEmail(OrderEntity order){
        String text = "Congrats! Your order have been placed. Following are the details: '\n'" +
                "Order id = " + order.getOrderId() + "\n" +
                "Order Total =" + order.getOrderTotal() +"\n" +
                "Order Date = " +order.getOrderDate();
        SimpleMailMessage mail= new SimpleMailMessage();
        mail.setTo(order.getCustomer().getEmailId());
        mail.setFrom("backendpractice123@gmail.com");
        mail.setSubject("Order Placed");
        mail.setText(text);

        javaMailSender.send(mail);

    }
}
