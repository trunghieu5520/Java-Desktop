/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ThinkPro
 */
public class Validate {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    public int getInt(String msg, String err, int min, int max) {
        int num = 0;
        boolean check;
        do {
            check = true;
            try {
                System.out.print(msg);
                num = Integer.parseInt(in.readLine());
                if (num < min || num > max) {
                    System.out.println("Number entered out of range");
                    check = false;
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println(err);
                check = false;
            }
        } while (!check);
        return num;
    }
    
    public double getDouble(String msg, String err, double min, double max) {
        double num = 0;
        boolean check;
        do {
            check = true;
            try {
                System.out.print(msg);
                num = Double.parseDouble(in.readLine());
                if (num < min || num > max) {
                    System.out.println("Number entered out of range");
                    check = false;
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println(err);
                check = false;
            }
        } while (!check);
        return num;
    }
    
    public String getDate(String msg, String err,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        String value="";
        sdf.setLenient(false); //Kiem tra ngay 1 cach nghiem ngat hon
        while(true){
            try {
                System.out.println(msg);
                
                value = in.readLine();
                date = sdf.parse(value);
                
                //xu ly nhap khong qua so voi ngay hien tai
                Date now = new Date();
                if(date.after(now)){
                    System.out.println("Date can be bigger than date now.");
                    continue;
                }
                break;
            } catch (IOException | ParseException ex) {
                System.out.println(err);
            }
            
        }
        return value;
    }
    
    public String getString(String regex, String msg) throws IOException{
        String s = null;
        boolean check;
        do{
            check = true;
            System.out.println(msg);
            s = in.readLine();
            if(!s.matches(regex)){
                check = false;
            }   
        }while(check==false);
        return s;
    }
}