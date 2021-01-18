package com.sapo.edu.ex9.controller;

import com.sapo.edu.ex9.model.Product;
import com.sapo.edu.ex9.service.ProductService;
import com.sapo.edu.ex9.service.RabbitMQSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api-rabbitmq/")
public class ApiSendProduct {

    private final RabbitMQSender rabbitMQSender;
    private final ProductService productService;

    public ApiSendProduct(RabbitMQSender rabbitMQSender, ProductService productService) {
        this.rabbitMQSender = rabbitMQSender;
        this.productService = productService;
    }

    @PostMapping(value = "/producer")
    public String producer(@RequestParam("idProduct") Integer idProduct, @RequestParam("amountSell") Integer amountSell) {

        Product product=new Product();
        product.setIdProduct(idProduct);
        product.setAmountSell(amountSell);
        product.setDateExpress(LocalDateTime.now());
        rabbitMQSender.send(product);

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

}