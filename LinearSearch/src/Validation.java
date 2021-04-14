
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vananh
 */
public class Validation {
    
    Scanner input = new Scanner(System.in);
    
    
    public int getInt(int min, int max) {
        do {            
            try {
                int value = Integer.parseInt(input.nextLine().trim());
                if(value < min || value > max) {
                throw new NumberFormatException();
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value !");
                System.out.print("Enter again: ");
            }
        } while (true);
    }
}
