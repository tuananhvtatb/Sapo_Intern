package demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.print("Nhap 1 cau hoac doan van:");
        String strChecked = sc.nextLine();

        int chon;
        do {

            do {
                System.out.println("\n\n\n=======================MENU=======================");
                System.out.println("1. Tim kiem trong chuoi vua nhap.");
                System.out.println("2. Tim kiem khong phan biet chu hoa chu thuong.");
                System.out.println("3. Dem so lan xuat hien trong chuoi vua nhap.");
                System.out.println("4. Noi them vao dau chuoi.");
                System.out.println("5. Noi them vao cuoi chuoi.");
                System.out.println("6. Kiem tra chuoi co rong hay khong?");
                System.out.println("7. Xoa chuoi.");
                System.out.println("8. Thay the chuoi.");
                System.out.println("9. Thoat");
                System.out.print("Chon yeu cau muon thuc hien(chi nhap tu 1 den 9!):");
                chon = check();
                if (chon > 9) {
                    System.out.println("Menu khong co yeu cau nay!");
                }
            } while (chon > 9);
            switch (chon) {
                case 1:
                    System.out.print("Nhap chuoi can tim:");
                    String str = sc.nextLine();
                    checkContainsAny(strChecked, str);
                    break;
                case 2:
                    System.out.print("Nhap chuoi can tim:");
                    String str1 = sc.nextLine();
                    checkContainsIgnoreCase(strChecked, str1);
                    break;
                case 3:
                    System.out.print("Nhap chuoi can dem:");
                    String str2 = sc.nextLine();
                    findCounterMatches(strChecked, str2);
                    break;
                case 4:
                    System.out.println("Nhap chuoi noi:");
                    String str3 = sc.nextLine();
                    strChecked = StringUtils.prependIfMissingIgnoreCase(strChecked, str3);
                    System.out.println("String after appending prefix:" + strChecked);
                    break;
                case 5:
                    System.out.println("Nhap chuoi noi:");
                    String str4 = sc.nextLine();
                    strChecked = StringUtils.appendIfMissingIgnoreCase(strChecked, str4);
                    System.out.println("String after appending suffix:" + strChecked);
                    break;
                case 6:
                    System.out.println("Nhap chuoi can kiem tra:");
                    String str5 = sc.nextLine();
                    if (StringUtils.isNotBlank(str5)) {
                        System.out.println("String inputted is not blank.");
                    } else {
                        System.out.println("String inputted is blank.");
                    }
                    break;
                case 7:
                    if (StringUtils.isNotBlank(strChecked)) {
                        System.out.println("Nhap chuoi xoa:");
                        String str6 = sc.nextLine();
                        if (StringUtils.containsIgnoreCase(strChecked, str6)) {
                            strChecked = StringUtils.removeIgnoreCase(strChecked, str6);
                            System.out.println("String after remove:" + strChecked);
                        } else {
                            System.out.println("Not find out \"" + str6 + "\" on \"" + strChecked + "\"");
                        }
                    } else {
                        System.out.println("Chuoi rong!");
                    }
                    break;
                case 8:
                    if (StringUtils.isNotBlank(strChecked)) {
                        System.out.println("Nhap chuoi can thay the:");
                        String str7 = sc.nextLine();
                        if (StringUtils.containsIgnoreCase(strChecked, str7)) {
                            System.out.println("Nhap chuoi thay the:");
                            String str8 = sc.nextLine();
                            strChecked = StringUtils.replaceIgnoreCase(strChecked, str7, str8);
                            System.out.println("String after replace:" + strChecked);
                        } else {
                            System.out.println("Not find out \"" + str7 + "\" on \"" + strChecked + "\"");
                        }
                    } else {
                        System.out.println("Chuoi rong!");
                    }
                    break;
            }
        } while (chon != 9);
    }

    public void checkContainsAny(String strCheck, String strIsChecked) {
        if (StringUtils.containsAny(strCheck, strIsChecked)) {
            System.out.println("Find out \"" + strIsChecked + "\" on \"" + strCheck + "\"");
        } else {
            System.out.println("Not find out \"" + strIsChecked + "\" on \"" + strCheck + "\"");
        }
    }

    public void checkContainsIgnoreCase(String strCheck, String strIsChecked) {
        if (StringUtils.containsIgnoreCase(strCheck, strIsChecked)) {
            System.out.println("Find out \"" + strIsChecked + "\" on \"" + strCheck + "\"");
        } else {
            System.out.println("Not find out \"" + strIsChecked + "\" on \"" + strCheck + "\"");
        }
    }

    public void findCounterMatches(String strCheck, String strIsChecked) {

        System.out.println("\"" + strIsChecked + "\"" + " appear " + StringUtils.countMatches(strCheck, strIsChecked) + " time in " + "\"" + strCheck + "\"");
    }

    public void appendSuffix(String strCheck, String strIsChecked) {
        System.out.println("\"" + strCheck + "\"" + " after appending suffix:" + StringUtils.appendIfMissing(strCheck, strIsChecked));
    }

    public void appendPrefix(String strCheck, String strIsChecked) {
        System.out.println("\"" + strCheck + "\"" + " after appending prefix:" + "\"" + StringUtils.prependIfMissing(strCheck, strIsChecked) + "\"");
    }

    public static int check() {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        boolean check = false;
        while (!check) {
            try {
                n = sc.nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Du lieu nhap vao khong dung! ");
                sc.nextLine();
            }
        }
        return n;
    }
}