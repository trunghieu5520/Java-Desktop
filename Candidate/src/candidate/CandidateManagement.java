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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class CandidateManagement {
    List<Candidate> listCandidates = new ArrayList<>();
    Validation validate = new Validation();

    public int indexID(int id) {
        for (int i = 0; i < listCandidates.size(); i++) {
            if (listCandidates.get(i).getId() == id) {
                return i;   // i trả về cái gì ? ( đối tượng )
            }
        }
        return -1;
    }

    public void addCandidate(int type) {
        int id;
        do {
            id = validate.getInt("Enter ID: ", "Id is a positive integer", 1, Integer.MAX_VALUE);
            if(indexID(id) != -1){
                System.out.println("Id is exist. Please enter another ID");
            }
        } while (indexID(id) != -1);

        String firstName = validate.getString("Enter First Name: ", "[a-zA-Z ]+");

        String lastName = validate.getString("Enter Last Name: ", "[a-zA-Z ]+");

        int birthDate = validate.getInt("Enter birth date: ", "BirthDate invalid", 1900, 2020); // check date không đc fit là 2020 thêm thư viện calendar, chọn calendar year ( lấy theo lịch máy tính )

        String address = validate.getString("Enter address: ", "[a-zA-Z ]+");

        String phone = validate.getString("Enter phone: ", "\\d{10,}");

        String email = validate.getString("Enter email: ", "^[a-zA-Z]\\w+@(\\w+\\.)+\\w+$");  // hỏi regex email

        switch (type) {
            case 0:
                int yearOfExp = validate.getInt("Enter year of experience: ", "Year invalid", 1, 2020-birthDate); // năm kinh nghiệm ko đc lớn hơn tuổi
                String proSkill = validate.getString("Enter pro skill", "[a-zA-Z ]+");

                Candidate E = new Experience(id, firstName, lastName, birthDate, address, phone, email, type, yearOfExp, proSkill);
                listCandidates.add(E);
                break;
            case 1:

                int graTime = validate.getInt("Enter Graduation Time: ", "Graduation Time invalid", 1900, 2020); // cái này cũng dùng Calender(YEAR) để lấy năm tối đa
                
                String rank;
                do {
                    rank = validate.getString("Enter Rank of Graduation: ", "[a-zA-Z]+");
                } while (!rank.equals("Excellence") && !rank.equals("Good") && !rank.equals("Fair") && !rank.equals("Poor"));
                
                String education = validate.getString("Enter education: ", "[a-zA-Z ]+");
                
                Candidate F = new Fresher(id, firstName, lastName, birthDate, address, phone, email, type, graTime, rank, education);
                listCandidates.add(F);
                break;
            case 2:
                String major = validate.getString("Enter education: ", "[a-zA-Z ]+");
                int semester = validate.getInt("Enter semester: ", "Semester is a positive integer", 1, Integer.MAX_VALUE);
                String university = validate.getString("Enter education: ", "[a-zA-Z ]+");
                Candidate I = new Intern(id, firstName, lastName, birthDate, address, phone, email, type, major, semester, university);
                listCandidates.add(I);
                break;

        }
    }
    
    public void search(){
    String inputSearch = validate.getString("Input Candidate name (First name or Last name):", "[a-zA-Z ]+");   // Hàm search thêm 1 biến cờ booelean 
                                                                                                      //đánh dấu nếu true thì search thành công ko thì trả về false và in thông báo
    
        int type = validate.getInt("Input type of candidate: ", "Type [0-2]", 0, 2);
        
        for (Candidate C : listCandidates) {
            if(C.getType()==type){
                String fullName = C.getFirsName().toLowerCase()+C.getLastName().toLowerCase();
                if(fullName.contains(inputSearch.toLowerCase())){
                    System.out.println(C);
                }
            }
        }
         
        
    }
    
    public void displayAll(){
        System.out.println("List of candidate:");
//        System.out.println("===========EXPERIENCE CANDIDATE============");
//        for (Candidate C : listCandidates) {
//            if(C instanceof Experience){
//                System.out.println(C.getFirsName()+" "+C.getLastName());
//            }
//        }
//        
//        System.out.println("==========FRESHER CANDIDATE==============");
//        for (Candidate C : listCandidates) {
//            if(C instanceof Fresher){
//                System.out.println(C.getFirsName()+" "+C.getLastName());
//            }
//        }
//        
//        System.out.println("===========INTERN CANDIDATE==============");
//        for (Candidate C : listCandidates) {
//            if(C instanceof Intern){
//                System.out.println(C.getFirsName()+" "+C.getLastName());
//            }
//        }

        //C2:
        Collections.sort(listCandidates, new Comparator<Candidate>() {     // có 2 cách in dùng cách nào cũng được
            @Override
            public int compare(Candidate o1, Candidate o2) {
               return o1.getType()-o2.getType();
            }
        });
        System.out.println("===========EXPERIENCE CANDIDATE============");
        int instance = 0;
        for (Candidate C : listCandidates) {
            if(C.getType()==1&&instance==0){
                System.out.println("==========FRESHER CANDIDATE==============");
                instance=1;
            }
            if(C.getType()==2&&instance==1){
                System.out.println("==========INTERN CANDIDATE==============");
                instance=2;
            }
            System.out.println(C.getFirsName()+" "+C.getLastName());
           
        }
        
    }

}
