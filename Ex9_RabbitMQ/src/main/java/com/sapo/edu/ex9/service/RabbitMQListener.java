package com.sapo.edu.ex9.service;

import com.sapo.edu.ex9.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener implements MessageListener {

    Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    public void onMessage(Message message) {
        logger.info("Consuming Message - '{}'",new String(message.getBody()));
        Product product = fromMessage(message);
    }
}
