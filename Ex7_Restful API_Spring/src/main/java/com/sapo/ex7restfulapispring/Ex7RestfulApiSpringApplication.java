package com.sapo.ex7restfulapispring;

import com.sapo.ex7restfulapispring.entities.User;
import com.sapo.ex7restfulapispring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Ex7RestfulApiSpringApplication{

    public static void main(String[] args) {
        SpringApplication.run(Ex7RestfulApiSpringApplication.class, args);
    }

}
