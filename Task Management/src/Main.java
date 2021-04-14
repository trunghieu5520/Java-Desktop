
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        TaskManagement taskManagement = new TaskManagement();
        Validate validate = new Validate();
        boolean loop = true;
        while (loop) {
            System.out.println("========= Task program =========\n"
                    + "1.	Add Task\n"
                    + "2.	Delete task\n"
                    + "3.	Display Task\n"
                    + "4.	exit\n"
                    + "");
            int option = validate.getInt("Enter option: ", "Option [1-4]", 1, 4);

            switch (option) {
                case 1:
                    taskManagement.AddTask();
                    break;
                case 2:
                    taskManagement.deleteTask();
                    break;
                case 3:
                    taskManagement.DisplayTask();
                    break;
                case 4:
                    String c ="";
                    System.out.println("Do you want to exit, enter Y to exit, another key to continue ");
                    c = sc.nextLine();
                    if(c.equalsIgnoreCase("Y")){
                        loop = false;
                        break;
                    }else{
                        continue;
                    }
                    
            }
        }
    }
}
