
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author trinh
 */
public class TpBank {

    //ham check account
    //ham check password
    //random capcha
    //check capcha
    Scanner sc = new Scanner(System.in); //shoud be add "UTF-8" before system.in

    public void checkAccount(ResourceBundle bundle) { //lấy dữ liệu từ file properties
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(bundle.getString("account"));
            String account = sc.nextLine();
            Pattern p = Pattern.compile("[0-9]{10}");
            if (p.matcher(account).find()) {
                break;
            } else {
                System.out.println(bundle.getString("check.account"));
            }
        }
    }

    public void checkPassword(ResourceBundle bundle) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(bundle.getString("password"));
            String password = sc.nextLine();
            Pattern p1 = Pattern.compile("^[0-9A-Za-z]{8,31}$"); //check 8-31
            Pattern p2 = Pattern.compile("^[A-Za-z0-9]*[0-9]+[A-Za-z0-9]*$");//CHECK CO IT NHAT LA 1 SO
            Pattern p3 = Pattern.compile("^[A-Za-z0-9]*[A-Za-z]+[A-Za-z0-9]*$");
            if (p1.matcher(password).find() && p2.matcher(password).find() && p3.matcher(password).find()) {
                break;
            } else {
                System.out.println(bundle.getString("wrong.password"));
            }
        }
    }

//    public String radomCapchar() {
//        //capchar: length = 5
//        //cau tao: chua hoa, chu thuong, so
//        //[a-zA-Z0-9]
//        String root = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
//        //eplDB = capcha
//        String capcha = "";
//        int length = root.length();
//        Random r = new Random();
//        //r.nextInt(10); 0-9
//        for (int i = 0; i < 5; i++) {
//            int index = r.nextInt(length);//0 - length - 1
//            capcha = capcha + root.charAt(index);
//        }
//        System.out.println("Capchar: " + capcha);
//        return capcha;
//    }
    public StringBuffer randomCapcha() {
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        boolean[] free = new boolean[alphabet.length() + 1]; //Cộng thêm 1 cho dư thôi
        Arrays.fill(free, true);
        int length = alphabet.length(); 
        Random r = new Random();
        StringBuffer capcha = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            while (true) {
                //Trong vòng lặp while này mình sẽ tìm ra một index mà chưa dùng bao giờ(free[index]=true)
                int index = r.nextInt(length);
                if (free[index]) {
                    //Tìm được thì thêm nó vào capcha và set free[index] = false để lần sau không dùng nữa
                    free[index] = false;
                    char c = alphabet.charAt(index);
                    capcha.append(c);
                    //Break ra khỏi vòng lặp vì trên kia để while(true) nên tìm được thì mình tự break
                    break;
                }
            }
        }
        return capcha;
    }

    public void checkCapchar(ResourceBundle bundle) {
        String capcha = "";
        while (true) {
            System.out.println(bundle.getString("capcha"));
            capcha = randomCapcha().toString();
            System.out.println(capcha);
            String input = "";
            input = sc.nextLine();
            if (capcha.equals(input)) {
                System.out.println(bundle.getString("right.capcha"));
                break;
            } else {
                System.out.println(bundle.getString("wrong.capcha"));
            }
        }
    }
}
