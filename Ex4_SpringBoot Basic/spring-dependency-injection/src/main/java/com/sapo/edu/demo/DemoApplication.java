package com.sapo.edu.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final Atm bidvAtm;

    public DemoApplication(Atm bidvAtm) {
        this.bidvAtm = bidvAtm;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();

        bidvAtm.customerInputInformation(customer);
        bidvAtm.exchange(customer);

    }
}
