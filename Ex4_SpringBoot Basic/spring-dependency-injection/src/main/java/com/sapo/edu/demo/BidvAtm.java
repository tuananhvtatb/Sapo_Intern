package com.sapo.edu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.math.BigDecimal;
import java.util.Scanner;

@Component
@Profile(value = "dev")
public class BidvAtm implements Atm {

    @Value("${atm.money}")
    private BigDecimal moneyAtm;

    @Autowired
    private Printer printer;

    @Autowired
    private Inputer inputer;

    public void customerInputInformation(Customer customer) {
        inputer.inputCustomer(customer);
    }

    @Override
    public void withDraw(Customer customer, BigDecimal amount) {
        if (amount == null || amount.compareTo(new BigDecimal(0)) < 0) {
            printer.printMessage("Amount is invalid");
            return;
        } else {
            if (amount.compareTo(moneyAtm) >= 0) {
                printer.printMessage("ATM is out of money");
                System.exit(0);
                return;
            }
        }
        if (customer.getBalance().compareTo(amount) < 0) {
            printer.printMessage("Insufficient Balance");
            return;
        } else {
            printer.printMessage("Exchange succesful!");
        }
        BigDecimal currentBalance = customer.getBalance();
        customer.setBalance(currentBalance.subtract(amount));
        moneyAtm = moneyAtm.subtract(amount);
    }

    @Override
    public void printCurrentMoney() {
        printer.printMessage("Current ATM money is " + moneyAtm.toString());
    }

    @Override
    public void deposit(Customer customer, BigDecimal amount) {
        if (amount == null || amount.compareTo(new BigDecimal(0)) <= 0) {
            printer.printMessage("Amount is invalid");
            return;
        } else {
            BigDecimal currentBalance = customer.getBalance();
            customer.setBalance(currentBalance.add(amount));
            moneyAtm = moneyAtm.add(amount);
            printer.printMessage("Deposit successful! Balance:" + String.valueOf(customer.getBalance()));
        }


    }

    @Override
    public void displayCustomerInfo(Customer customer) {
        printer.printCustoner(customer);
    }

    @Override
    public void exchange(Customer customer) {
        int chon;
        do {
            printer.printMessage("============Select a request==========");
            printer.printMessage("1. Withdraw money");
            printer.printMessage("2. Deposit money");
            printer.printMessage("3. Display information");
            printer.printMessage("4. Exit");
            printer.printMessage("Select:");
            chon = check();

            switch (chon) {
                case 1:

                    printer.printMessage("Input amount:");
                    BigDecimal amount = sc.nextBigDecimal();
                    withDraw(customer, amount);
                    selectionContinue();
                    break;
                case 2:
                    printer.printMessage("Input money you want to deposit:");
                    BigDecimal moneySend = sc.nextBigDecimal();
                    deposit(customer, moneySend);
                    selectionContinue();
                    break;
                case 3:
                    displayCustomerInfo(customer);
                    selectionContinue();
                    break;
                case 4:
                    break;
                default:
                    printer.printMessage("Don't this request!");
            }
        } while (chon != 4);
    }

    public int check() {
        int n = 0;
        boolean check = false;
        while (!check) {
            try {
                n = sc.nextInt();
                check = true;
            } catch (Exception e) {
                printer.printMessage("Input only number! ");
                printer.printMessage("Select again:");
                sc.nextLine();
            }
        }
        return n;
    }

    public void selectionContinue() {
        printer.printMessage("Do you want to execute another exchange?(Y/N):");
        sc.nextLine();
        String s = sc.nextLine();
        if (s.equalsIgnoreCase("N")) {
            System.exit(0);
        }
    }
}
