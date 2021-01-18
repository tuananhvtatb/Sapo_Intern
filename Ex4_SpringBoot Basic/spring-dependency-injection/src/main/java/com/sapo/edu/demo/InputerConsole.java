package com.sapo.edu.demo;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputerConsole implements Inputer {
    Scanner sc = new Scanner(System.in);

    @Override
    public void inputCustomer(Customer customer) {
        System.out.print("Input number account:");
        customer.setAcctNo(sc.nextLine());
        System.out.print("PIN code:");
        customer.setPin(sc.nextLine());
        System.out.print("Input balance:");
        customer.setBalance(sc.nextBigDecimal());
        sc.nextLine();
    }
}
