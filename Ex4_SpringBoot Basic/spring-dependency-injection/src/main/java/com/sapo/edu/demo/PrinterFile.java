package com.sapo.edu.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PrinterFile implements Printer {
    Logger logger = LoggerFactory.getLogger(PrinterFile.class);

    @Override
    public void printCustoner(Customer customer) {
        String message = "CustomerId: " + customer.getAcctNo() + ", balance: " + customer.getBalance().toString();
        printMessage(message);
    }

    @Override
    public void printMessage(String message) {
        logger.info(message);
    }
}
