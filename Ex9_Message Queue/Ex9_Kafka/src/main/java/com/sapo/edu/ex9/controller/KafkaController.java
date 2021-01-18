package com.sapo.edu.ex9.controller;

import com.sapo.edu.ex9.model.ProductModel;
import com.sapo.edu.ex9.service.ProductSender;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final ProductSender productSender;

    KafkaController(ProductSender productSender) {
        this.productSender = productSender;
    }

    @PostMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam("id") Integer id,
                                          @RequestParam("id_product") Integer idProduct,
                                          @RequestParam("amount_sell") Integer amountSell) {
        ProductModel product = new ProductModel();
        product.setIdProduct(idProduct);
        product.setAmountSell(amountSell);
        product.setDateExpress(LocalDateTime.now());
        productSender.send(product);
        return "OK";
    }
}