package blottersystemsarno;
import java.util.Scanner;

public class Reports {

    public void incidentTransaction() {
        Scanner sc = new Scanner(System.in);
        String response;

        do {
            System.out.println("|-------------------|");
            System.out.println("|   WELCOME TO REPORTS   |");
            System.out.println("|-------------------|");
            System.out.println("");
            System.out.println("1. [VIEW REPORTS]");
            System.out.println("2. [EXIT]");
            System.out.print("Enter your choice: ");
            response = sc.nextLine();

            switch (response) {
                case "1":
                    viewReports(); 
                    break;
                case "2":
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (!response.equals("2"));

        sc.close();
    }

 private void viewReports() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    
    System.out.println("Select the criteria to filter reports:");
    System.out.println("1. Complainant");
    System.out.println("2. Entry Status");
    System.out.println("3. Officer");
    System.out.println("4. Suspect");
    System.out.println("5. Incident Type");
    System.out.println("6. Incident Date");
    System.out.println("7. Incident Place");
    System.out.println("8. View All Reports");
    System.out.print("Enter your choice: ");
    
    int choice = sc.nextInt();
    sc.nextLine(); 

    String query = "";
    String[] headers = {};
    String[] columns = {};

    switch (choice) {
        case 1:  
    System.out.print("Enter complainant name: ");
    String complainantName = sc.nextLine();
    query = "SELECT * FROM tbl_report WHERE complainant_name = ?";
    headers = new String[]{"ReportID", "Complainant", "Report Details"};
    columns = new String[]{"report_id", "complainant_name", "report_details"};
    
    break;
case 2:  
    System.out.print("Enter entry status: ");
    String status = sc.nextLine();
    query = "SELECT * FROM tbl_report WHERE entry_status = ?";
    headers = new String[]{"ReportID", "Entry Status", "Report Details"};
    columns = new String[]{"report_id", "entry_status", "report_details"};
    
    break;
case 3: 
    System.out.print("Enter officer name: ");
    String officerName = sc.nextLine();
    query = "SELECT * FROM tbl_report WHERE officer_name = ?";
    headers = new String[]{"ReportID", "Officer", "Report Details"};
    columns = new String[]{"report_id", "officer_name", "report_details"};
    break;
case 4:  
    System.out.print("Enter suspect name: ");
    String suspectName = sc.nextLine();
    query = "SELECT * FROM tbl_report WHERE suspect_name = ?";
    headers = new String[]{"ReportID", "Suspect", "Report Details"};
    columns = new String[]{"report_id", "suspect_name", "report_details"};
    break;
case 5:  
    System.out.print("Enter incident type: ");
    String incidentType = sc.nextLine();
    query = "SELECT * FROM tbl_report WHERE incident_type = ?";
    headers = new String[]{"ReportID", "Incident Type", "Report Details"};
    columns = new String[]{"report_id", "incident_type", "report_details"};
    break;
case 6:  
    System.out.print("Enter incident date (yyyy-mm-dd): ");
    String incidentDate = sc.nextLine();
    query = "SELECT * FROM tbl_report WHERE incident_date = ?";
    headers = new String[]{"ReportID", "Incident Date", "Report Details"};
    columns = new String[]{"report_id", "incident_date", "report_details"};
    break;
case 7: 
    System.out.print("Enter incident place: ");
    String incidentPlace = sc.nextLine();
    query = "SELECT * FROM tbl_report WHERE incident_place = ?";
    headers = new String[]{"ReportID", "Incident Place", "Report Details"};
    columns = new String[]{"report_id", "incident_place", "report_details"};
    break;
case 8:  
    query = "SELECT * FROM tbl_report";
    headers = new String[]{"ReportID", "Complainant", "Officer", "Suspect", "Incident Type", "Incident Date", "Incident Place", "Report Details"};
    columns = new String[]{"report_id", "complainant_name", "officer_name", "suspect_name", "incident_type", "incident_date", "incident_place", "report_details"};
    conf.viewRecords(query, headers, columns);
    break;
default:
    System.out.println("Invalid choice. Please try again.");
    return;
}
 }
}