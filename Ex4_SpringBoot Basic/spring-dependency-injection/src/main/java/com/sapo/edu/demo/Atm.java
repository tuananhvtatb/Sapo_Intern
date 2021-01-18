package com.sapo.edu.demo;

import java.math.BigDecimal;
import java.util.Scanner;

public interface Atm {
    Scanner sc = new Scanner(System.in);

    void customerInputInformation(Customer customer);

    void withDraw(Customer customer, BigDecimal amount);

    void printCurrentMoney();

    void deposit(Customer customer, BigDecimal amount);

    void displayCustomerInfo(Customer customer);

    void exchange(Customer customer);

}
