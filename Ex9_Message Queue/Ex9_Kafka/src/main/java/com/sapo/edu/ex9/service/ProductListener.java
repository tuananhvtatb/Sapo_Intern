package com.sapo.edu.ex9.service;

import com.sapo.edu.ex9.entity.Product;
import com.sapo.edu.ex9.model.ProductModel;
import com.sapo.edu.ex9.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ProductListener {

    private static final Logger logger = LoggerFactory.getLogger(ProductListener.class);
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ProductListener(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @KafkaListener(topics = "pro",groupId = "group_id")
    public void receive(@Payload ProductModel data,
                        @Headers MessageHeaders headers) {
        logger.info("received data='{}','{}','{}','{}'", data.getId(),data.getIdProduct(),data.getAmountSell(),data.getDateExpress());

        productRepository.save(modelMapper.map(data, Product.class));

        headers.keySet().forEach(key -> {
            logger.info("{}: {}", key, headers.get(key));
        });
    }

    @KafkaListener(topics = "pro",groupId = "group_id2")
    public void receive2(@Payload ProductModel data,
                        @Headers MessageHeaders headers) {
        logger.info("received data='{}','{}','{}','{}'", data.getId(),data.getIdProduct(),data.getAmountSell(),data.getDateExpress());

        productRepository.save(modelMapper.map(data, Product.class));

        headers.keySet().forEach(key -> {
            logger.info("{}: {}", key, headers.get(key));
        });
    }

}
