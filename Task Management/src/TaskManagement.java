/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ThinkPro
 */
public class TaskManagement {
    List<Manager> listTask = new ArrayList<>();
    Validate validate = new Validate();
    
    public void AddTask() {
        try {
            System.out.println("------------Add Task---------------");
            int id = 1;
            if(!listTask.isEmpty()){
                id =listTask.get(listTask.size()-1).getId()+1;
            }          
            String name = validate.getString("[a-zA-Z ]+", "Requirement Name: ");
            
            int typeId = validate.getInt("Task Type: ", "Please enter a positive number", 1, 4);
            String date = validate.getDate("Date: ", "Date invalid, enter day-month-year", "dd-MM-yyyy");
            double from = validate.getDouble("From: ", "Please enter a real number", 8.0, 17.5);
            double to = validate.getDouble("To: ", "Please enter a real number", 8.0, 17.5);
            String assignee = validate.getString("[a-zA-Z ]+", "Assignee: ");
            String reviewer = validate.getString("[a-zA-Z ]+", "Reviewer: ");
            Manager task = new Manager(id, name, typeId, date, from, to, assignee, reviewer);
            listTask.add(task);
            
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public void DisplayTask(){
        System.out.println("----------------------------------------- Task ---------------------------------------");
        System.out.println("ID\t\tName\t\tTask Type\t\tDate\t\tTime\t\tAssignee\t\tReviewer");
        for (Manager task : listTask) {
            System.out.println(task.toString());
        }
    }
    
    public void deleteTask(){
        System.out.println("---------Del Task------");
        int idDelete = validate.getInt("ID: ", "Please enter a positive number", 1, listTask.size());
        listTask.remove(idDelete-1);
        for (int i = idDelete-1; i < listTask.size(); i++) {
            listTask.get(i).setId(i+1);
        }
        System.out.println("Delete successful");
//        boolean isIdExist=false;
//        for (Manager task : listTask) {
//            if(task.getId()==idDelete){
//                listTask.remove(task);
//                isIdExist=true;
//                System.out.println("Delete successfull");
//                break;
//            }
//        }
//        
//        if(!isIdExist){
//            System.out.println("Id not found. Delete failed");
//        }
    }
    
}
