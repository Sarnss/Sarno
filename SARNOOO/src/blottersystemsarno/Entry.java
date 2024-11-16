package blottersystemsarno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Entry {
    
    public void entryTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
        System.out.println("|----------------|");    
        System.out.println("|WELCOME TO ENTRY|");
        System.out.println("|----------------|");
        System.out.println("");
        System.out.println("1. [ADD ENTRY]");
        System.out.println("2. [VIEW ENTRY]");
        System.out.println("3. [UPDATE ENTRY]");
        System.out.println("4. [DELETE ENTRY]");
        System.out.println("5. [EXIT]");
        
        System.out.print("Enter Action: ");
        int action = sc.nextInt();
        Entry en = new Entry ();

        switch(action){
            case 1:
                en.addEntry();
                break;
            case 2:       
                en.viewEntry();
                break;
            case 3:
                en.viewEntry();
                en.updateEntry();
                en.viewEntry();
                break;
            case 4:
                en.viewEntry();
                en.deleteEntry();
                en.viewEntry();    
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    

    public void addEntry() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        LocalDate currDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currDate.format(dateFormat);
        
        LocalTime currTime = LocalTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = currTime.format(timeFormat);

        
        System.out.print("Entry Status: ");
        String status = sc.nextLine();

        
        String sql = "INSERT INTO tbl_entry (e_time, e_date, e_status) VALUES (?, ?, ?)";
        conf.addRecord(sql, date, time, status);

       
    }
    
    public void viewEntry() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_entry";
        String[] Headers = {"EntryID","Date", "Time", "Entry Status"};
        String[] Columns = {"e_id", "e_date", "e_time", "e_status"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateEntry() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.println("Enter the ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        while (conf.getSingleValue("SELECT e_id FROM tbl_entry WHERE e_id = ?", id) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Select Entry ID Again: ");
            id = sc.nextInt();
            sc.nextLine(); 
        }
        
        System.out.print("Entry New Status: ");
        String nstatus = sc.nextLine();
    
        String qry = "UPDATE tbl_entry SET e_status = ? WHERE e_id = ?";
        conf.updateRecord(qry, nstatus, id);

 
    }

    
    private void deleteEntry() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter the ID to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT e_id FROM tbl_entry WHERE e_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Entry ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_entry WHERE e_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}


