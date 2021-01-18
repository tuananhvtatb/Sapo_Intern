package com.sapo.edu.ex9.service;

import com.sapo.edu.ex9.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private final AmqpTemplate rabbitTemplate;
    Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);

    @Value("${sapo.rabbitmq.exchange}")
    private String exchange;

    @Value("${sapo.rabbitmq.routingkey}")
    private String routingkey;

    public RabbitMQSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Product product) {
        rabbitTemplate.convertAndSend(exchange, routingkey, product);
        logger.info("Send msg = '{}'",product);

    }
}
