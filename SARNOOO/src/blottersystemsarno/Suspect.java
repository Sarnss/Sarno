package blottersystemsarno;

import java.util.Scanner;

public class Suspect {
    
    public void sTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
        System.out.println("|------------------|");    
        System.out.println("|WELCOME TO SUSPECT|");
        System.out.println("|------------------|");
        System.out.println("");
        System.out.println("1. [ADD SUSPECT]");
        System.out.println("2. [VIEW SUSPECT]");
        System.out.println("3. [UPDATE SUSPECT]");
        System.out.println("4. [DELETE SUSPECT]");
        System.out.println("5. [EXIT]");
        
        System.out.print("Enter Action: ");
        int action = sc.nextInt();
        Suspect st = new Suspect ();

        switch(action){
            case 1:
                st.addSuspects();
                break;
            case 2:       
                st.viewSuspects();
                break;
            case 3:
                st.viewSuspects();
                st.updateSuspects();
                st.viewSuspects();
                break;
            case 4:
                st.viewSuspects();
                st.deleteSuspects();
                st.viewSuspects();    
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addSuspects () { 
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Suspect Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        System.out.print("Gender: ");
        String gender = sc.next();
        System.out.print("Address: ");
        String add = sc.next();

        String sql = "INSERT INTO tbl_suspect (s_name, s_age, s_gender, s_address) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, name, age, gender, add);


    }

    public void viewSuspects() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_suspect";
        String[] Headers = {"SuspectID","Suspect", "Age", "Gender", "Address"};
        String[] Columns = {"s_id", "s_name", "s_age", "s_gender", "s_address"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateSuspects() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter the ID to update: ");
        int id = sc.nextInt();
  
        while(conf.getSingleValue("SELECT s_id FROM tbl_suspect WHERE s_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Suspect ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.print("New Suspect Name: ");
        String nname = sc.next();
        System.out.print("New Age: ");
        int nage = sc.nextInt();
        System.out.print("New Gender: ");
        String ngender = sc.next();
        System.out.print("New Address: ");
        String nadd = sc.next();
        String qry = "UPDATE tbl_suspect SET s_name = ?, s_age = ?, s_gender = ?, s_address = ? WHERE s_id = ?";
        
        
        conf.updateRecord(qry, nname, nage, ngender, nadd, id);         
        
        
    }
    
    private void deleteSuspects() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter the ID to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT s_id FROM tbl_suspect WHERE s_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Suspect ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_suspect WHERE s_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}



