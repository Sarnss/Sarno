package blottersystemsarno;

import java.util.Scanner;

public class Reports {

    public void incidentTransaction() {
        Scanner sc = new Scanner(System.in);
        String response = null;
        do {
            System.out.println("|-----------------------|");
            System.out.println("|   REPORTS MENU        |");
            System.out.println("|-----------------------|");
            System.out.println("1. [VIEW ALL COMPLAINANTS]");
            System.out.println("2. [VIEW ALL ENTRY STATUS]");
            System.out.println("3. [VIEW ALL OFFICERS]");
            System.out.println("4. [VIEW ALL SUSPECTS]");
            System.out.println("5. [EXIT]");
            System.out.print("Enter Action: ");

            int action = -1;
            try {
                action = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (action) {
                case 1:
                    viewComplainants();
                    break;
                case 2:
                    viewEntryStatus();
                    break;
                case 3:
                    viewOfficers();
                    break;
                case 4:
                    viewSuspects();
                    break;
                case 5:
                    System.out.println("Exiting Reports Menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 5.");
            }

            System.out.println("Do you want to view another report? (yes/no)");
            response = sc.nextLine();
        } while (response.equalsIgnoreCase("yes"));
    }

    public void viewComplainants() {
        config conf = new config();
        String query = "SELECT * FROM tbl_complainant";
        String[] headers = {"ComplainantID", "Name", "Contact Info", "Address"};
        String[] columns = {"c_id", "c_name", "c_contact", "c_address"};
                conf.viewRecords(query, headers, columns);

    }

    public void viewEntryStatus() {
        config conf = new config();
        String query = "SELECT * FROM tbl_entry";
        String[] headers = {"EntryID", "Date", "Time", "Entry Status"};
        String[] columns = {"e_id", "e_date", "e_time", "e_status"};
                conf.viewRecords(query, headers, columns);

    }

    public void viewOfficers() {
        config conf = new config();
        String query = "SELECT * FROM tbl_officer";
        String[] headers = {"OfficerID", "Officer Name", "Department", "Contact Info", "Badge Number"};
        String[] columns = {"officer_id", "officer_name", "officer_department", "officer_contact", "officer_badgenumber"};
                conf.viewRecords(query, headers, columns);

    }

    public void viewSuspects() {
        config conf = new config();
        String query = "SELECT * FROM tbl_suspect";
        String[] headers = {"SuspectID", "Name", "Age", "Gender", "Address"};
        String[] columns = {"s_id", "s_name", "s_age", "s_gender", "s_address"};
            conf.viewRecords(query, headers, columns);

    }

   
    
}
