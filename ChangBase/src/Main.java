/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Main {
     public static void main(String[] args) {
        Validate validate = new Validate();
        Change change = new Change();
        while(true){
            
            int inBase = validate.getBase("Enter base input: ", "Base invalid");
            String value = validate.getValue("Enter value input:", "Value invalid", inBase);
            
            int outBase = validate.getBase("Enter base output", "Base invalid");
            System.out.println("After change base: ");
            System.out.println("The output:  ");
            if(inBase==10){
                System.out.println(change.DecToOther(Integer.parseInt(value), outBase));
            }else{
                int decTmp = change.OtherToDec(value, inBase);
                System.out.println(change.DecToOther(decTmp, outBase));
            }
            System.out.println("-----------------------------");
            //break; 
        }
    }
}
