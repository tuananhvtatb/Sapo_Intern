package com.sapo.edu.ex5dbspringboot;

import com.sapo.edu.ex5dbspringboot.manage.MainMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex5DbSpringbootApplication implements CommandLineRunner {

    private final MainMenu mainMenu;

    public Ex5DbSpringbootApplication(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public static void main(String[] args) {
        SpringApplication.run(Ex5DbSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainMenu.menuMain();
    }

}
