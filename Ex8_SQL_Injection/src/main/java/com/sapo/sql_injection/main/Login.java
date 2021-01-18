package com.sapo.sql_injection.main;

import com.sapo.sql_injection.dto.UserDTO;
import com.sapo.sql_injection.service.impl.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class Login {

    Logger logger = Logger.getLogger(String.valueOf(Logger.class));
    Scanner sc = new Scanner(System.in);

    private final UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    public void login(){
        logger.info("Nhap user name:");
        String username = sc.nextLine();
        logger.info("Nhap password:");
        String password = sc.nextLine();
        Boolean check = checkLogin(username, password);
        if(Boolean.TRUE.equals(check)){
            logger.info("Login success!");
        }else {
            logger.info("Login fail!");
        }
    }

    public Boolean checkLogin(String username, String password){
        boolean check = false;
        for (UserDTO userDTO : userService.getAllList()){
            if (userDTO.getUserName().equals(username) && userDTO.getPassword().equals(password)) {
                check = true;
                break;
            }
        }
        return  check;
    }
}
