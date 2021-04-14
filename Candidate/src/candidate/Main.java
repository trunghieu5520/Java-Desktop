/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candidate;

/**
 *
 * @author ASUS
 */
public class Main {
    
    public static void main(String[] args) {
        CandidateManagement manager = new CandidateManagement();
        
        manager.listCandidates.add(new Fresher(2, "Nguyen", "Thi Huong", 2001, 
                "HN", "0385739160", "Huongnt@gmail.com", 1, 2019, "Good", "FPT"));
        manager.listCandidates.add(new Intern(3, "Le", "Minh Nghia", 2001, 
                "TH", "123456789", "nghia@gmail.com", 2, "KTPM", 3, "FPT"));
        manager.listCandidates.add(new Experience(1, "Nguyen", "Trung Hieu", 1999, 
                "VN", "0866823499", "hieunthe141706@fpt.edu.vn", 0, 10, "Dev"));
        manager.listCandidates.add(new Experience(4, "Le", "Thien Van", 1999, 
                "LS", "0866823499", "vanlthe130736@fpt.edu.vn", 0, 10, "Dev"));
        manager.displayAll();
        Validation validate = new Validation();
        boolean loop = true;
        while (loop) {
            System.out.println("CANDIDATE MANAGEMENT SYSTEM\n"
                    + "1.	Experience\n"
                    + "2.	Fresher\n"
                    + "3.	Internship\n"
                    + "4.	Searching\n"
                    + "5.	Exit");
            int option = validate.getInt("Enter your option[1-5]: ", "Option[1-5]", 1, 5);
            switch (option) {
                case 1:
                    manager.addCandidate(0);
                    break;
                case 2:
                    manager.addCandidate(1);
                    break;
                case 3:
                    manager.addCandidate(2);
                    break;
                case 4:
                    manager.search();
                    break;
                case 5:
                    loop = false;
                    break;
               

            }
        }
    }
}
