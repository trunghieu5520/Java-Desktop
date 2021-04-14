
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vananh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Khởi tạo 1 đối tượng có tên pr  thuộc class Process
        Process pr = new Process();
        Validation check = new Validation();
        System.out.print("Enter number of array: ");
        int n = check.getInt(0, 1000);
         int [] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = new Random().nextInt(100);
        }
        pr.display(arr);
        System.out.println("\nEnter search value: ");
        int value = check.getInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        // khởi tạo 1 mảng các số nguyên 
       
        //pr.display(arr);
        int rel = pr.LinearSearch(arr, value);
        if(rel == -1) {
            System.out.println("\nNot found !");
        } else {
            System.out.println("\nFound "+value+" at index "+rel);
        }
    }
}
