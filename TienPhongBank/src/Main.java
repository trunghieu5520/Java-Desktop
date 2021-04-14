
import java.util.Locale;
import java.util.Locale.Builder;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author trinh
 */
public class Main {

    public static void menu() {
        System.out.println("-------Login Program-------");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
        System.out.println("Please choice one option:");
    }

    public static int getChoice() {
        Scanner sc = new Scanner(System.in, "UTF-8");
        while (true) {
            String txt = sc.nextLine();
            Pattern p = Pattern.compile("^[123]$");
            if (p.matcher(txt).find()) {
                int choice = Integer.parseInt(txt);
                return choice;
            } else {
                System.out.println("invalid choice, please input again: ");
            }
        }
    }

    public static void main(String[] args) {
//        Locale.Builder localeBuilder 
//            = new Builder();
        Scanner sc = new Scanner(System.in, "UTF-8");
        TpBank tp = new TpBank();
        while (true) {
            menu();
            int choice = getChoice();
            ResourceBundle bundle;
            Locale locale = new Locale("Vi", "VietNamese"); // phan biet file tieng Viet va file tieng anh
            Locale locale1 = new Locale("En", "English");
            switch (choice) {
                case 1:
                    bundle = ResourceBundle.getBundle("Vi",locale);              
                    System.out.println("Locale: " + locale);
                    tp.checkAccount(bundle);
                    tp.checkPassword(bundle);
                    tp.checkCapchar(bundle);
                    break;
                case 2:

                    bundle = ResourceBundle.getBundle("En",locale1);
                  System.out.println("Locale: " + locale1);
                    tp.checkAccount(bundle);
                    tp.checkPassword(bundle);
                    tp.checkCapchar(bundle);
                    break;
                case 3:
                    System.out.println("Do you want to exit ? Y for confirm, another key to continue");
                    String c = sc.nextLine();
                    if (c.equalsIgnoreCase("Y")) {
                        System.exit(0);
                    }
                    continue;
            }
        }
    }
}
