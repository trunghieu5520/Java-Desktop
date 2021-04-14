/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Manager {
    private int id;
    private String name;
    private int typeid;
    private String date;
    private double from;
    private double to;
    private String assignee;
    private String reviewer;
    //  ArrayList<TaskType> types;
    
    public Manager(){
        
    }

    public Manager(int id, String name, int typeid, String date, double from, double to, String assignee, String reviewer) {
        this.id = id;
        this.name = name;
        this.typeid = typeid;
        this.date = date;
        this.from = from;
        this.to = to;
        this.assignee = assignee;
        this.reviewer = reviewer;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public double getTime() {
        return to - from;
    }

    public String taskname() {
        String taskname = "";
        switch (typeid) {
            case 1:
                taskname = "Code";
                break;
            case 2:
                taskname = "Test";
                break;
            case 3:
                taskname = "Design";
                break;
            case 4:
                taskname = "Review";
                break;
        }
        return taskname;
    }

    @Override
    public String toString() {
        String out = String.format(id+"\t\t" +name+"\t\t" +taskname()+ "\t\t\t"+date+"\t\t"+ getTime()+"\t\t"+ assignee+"\t\t"+ reviewer);
        return out;
    }
}
