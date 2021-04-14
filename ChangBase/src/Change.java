/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Change {
   public int OtherToDec(String Other, int base) {
        int result=0;
        String HEX = "0123456789ABCDEF";
        Other = Other.toUpperCase();
        for(int i=0;i<Other.length();i++){
            result+=HEX.indexOf(Other.charAt(i))*Math.pow(base, Other.length()-1-i);
        }
        return result;
    }
    
    public String DecToOther(int Dec, int base) {
        String result="";
        String HEX = "0123456789ABCDEF";
        while(Dec>0){
            result=HEX.charAt(Dec%base)+result;
            Dec/=base;
        }
        return result;
    }
}
