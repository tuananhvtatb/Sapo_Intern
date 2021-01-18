package com.sapo.edu.ex9.service;

import com.sapo.edu.ex9.model.ProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProductSender {

    private static final Logger LOG = LoggerFactory.getLogger(ProductSender.class);

    private final KafkaTemplate<String, ProductModel> kafkaTemplate;

    public ProductSender(KafkaTemplate<String, ProductModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void send(ProductModel data){
        LOG.info("sending data='{}','{}','{}','{}' to topic='{}'",data.getId(),data.getIdProduct(),data.getAmountSell(),data.getDateExpress(), "pro");

        Message<ProductModel> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "pro")
                .build();
        kafkaTemplate.send(message);
    }
}
