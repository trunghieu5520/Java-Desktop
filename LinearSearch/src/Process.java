/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vananh
 */
public class Process {
    public void display(int [] arr) {
        System.out.print("The array: [");
        for(int i = 0; i < arr.length; i++) {
            if( i == arr.length - 1) {
                System.out.print(arr[arr.length - 1] + "]");
            } else {
                System.out.print(arr[i] + ",");
            }
        }
    }
    
    public int LinearSearch(int [] arr, int key) {
        for(int i =0; i < arr.length; i++) {
            if( arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
