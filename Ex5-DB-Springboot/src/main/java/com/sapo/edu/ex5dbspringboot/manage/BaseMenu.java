package com.sapo.edu.ex5dbspringboot.manage;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;
import java.util.logging.Logger;

public abstract class BaseMenu {
    Logger logger = Logger.getLogger(String.valueOf(BaseMenu.class));
    Scanner sc = new Scanner(System.in);

    int chon;

    public int check() {
        int n = 0;
        boolean check = false;
        while (!check) {
            try {
                n = sc.nextInt();
                check = true;
            } catch (Exception e) {
                logger.info("Chỉ nhập số! ");
                logger.info("Chọn lại:");
                sc.nextLine();
            }
        }
        return n;
    }

    public String checkString() {
        String strCheck = null;
        do {
            strCheck = sc.nextLine();
            if (StringUtils.isBlank(strCheck)) {
                logger.info("Nhập vào chưa hợp lệ! ");
                logger.info("Nhập lại:");
            }
        } while (StringUtils.isBlank(strCheck));
        return strCheck;
    }
}
