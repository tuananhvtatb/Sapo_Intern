package com.sapo.sql_injection.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlInjectionApplication implements CommandLineRunner {

    private final Login login;

    public SqlInjectionApplication(Login login) {
        this.login = login;
    }

    public static void main(String[] args) {
        SpringApplication.run(SqlInjectionApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        login.login();
    }

}
