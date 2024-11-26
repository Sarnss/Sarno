package blottersystemsarno;

import java.util.Scanner;

public class Officer {

    public void officerTransaction() {

        Scanner sc = new Scanner(System.in);
        String response = null;
        do {

            System.out.println("|------------------|");
            System.out.println("|WELCOME TO OFFICER|");
            System.out.println("|------------------|");
            System.out.println("");
            System.out.println("1. [ADD OFFICER]");
            System.out.println("2. [VIEW OFFICER]");
            System.out.println("3. [UPDATE OFFICER]");
            System.out.println("4. [DELETE OFFICER]");
            System.out.println("5. [EXIT]");

            System.out.print("Enter Action: ");
            int action = 0;

            if (sc.hasNextInt()) {
                action = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                sc.next();    
                continue;
            }

            Officer off = new Officer();

            switch (action) {
                case 1:
                    off.addOfficers();
                    break;
                case 2:
                    off.viewOfficers();
                    break;
                case 3:
                    off.viewOfficers();
                    off.updateOfficers();
                    off.viewOfficers();
                    break;
                case 4:
                    off.viewOfficers();
                    off.deleteOfficers();
                    off.viewOfficers();
                    break;
                case 5:
                    System.out.println("Thank You, See you soonest!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 5.");
            }

            System.out.println("Do you want to continue? (yes/no)");
            response = sc.next();
            while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
                System.out.print("Invalid response. Enter 'yes' to continue or 'no' to exit: ");
                response = sc.next();
            }
        } while (response.equalsIgnoreCase("yes"));

        System.out.println("Thank You, See you soonest!");
    }

    public void addOfficers() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Officer Name: ");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter Officer Name: ");
            name = sc.nextLine();
        }

        System.out.print("Department: ");
        String dept = sc.nextLine();
        while (dept.isEmpty()) {
            System.out.print("Department cannot be empty. Enter Department: ");
            dept = sc.nextLine();
        }

        System.out.print("Contact Info (numeric): ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid contact info. Enter a valid numeric Contact Info: ");
            sc.next();
        }
        int cont = sc.nextInt();

        System.out.print("Badge Number: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid badge number. Enter a valid numeric Badge Number: ");
            sc.next();
        }
        int bn = sc.nextInt();

        String sql = "INSERT INTO tbl_officer (officer_name, officer_department, officer_contact, officer_badgenumber) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, name, dept, cont, bn);
    }

    public void viewOfficers() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_officer";
        String[] Headers = {"OfficerID", "Officer", "Department", "Contact Information", "Badge Number"};
        String[] Columns = {"officer_id", "officer_name", "officer_department", "officer_contact", "officer_badgenumber"};

        conf.viewRecords(Query, Headers, Columns);
    }

    private void updateOfficers() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter the ID to update: ");
        int id = 0;

        while (!sc.hasNextInt()) {
            System.out.print("Invalid ID. Enter a valid numeric ID: ");
            sc.next();
        }
        id = sc.nextInt();
        sc.nextLine();   

        while (conf.getSingleValue("SELECT officer_id FROM tbl_officer WHERE officer_id = ?", id) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Select Officer ID Again: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid ID. Enter a valid numeric ID: ");
                sc.next();
            }
            id = sc.nextInt();
            sc.nextLine();   
        }

        System.out.print("New Officer Name: ");
        String nname = sc.nextLine();
        while (nname.isEmpty()) {
            System.out.print("Name cannot be empty. Enter New Officer Name: ");
            nname = sc.nextLine();
        }

        System.out.print("New Department: ");
        String ndept = sc.nextLine();
        while (ndept.isEmpty()) {
            System.out.print("Department cannot be empty. Enter New Department: ");
            ndept = sc.nextLine();
        }

        System.out.print("New Contact Info (numeric): ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid contact info. Enter a valid numeric Contact Info: ");
            sc.next();
        }
        int ncont = sc.nextInt();

        System.out.print("New Badge Number: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid badge number. Enter a valid numeric Badge Number: ");
            sc.next();
        }
        int nbn = sc.nextInt();

        String qry = "UPDATE tbl_officer SET officer_name = ?, officer_department = ?, officer_contact = ?, officer_badgenumber = ? WHERE officer_id = ?";
        conf.updateRecord(qry, nname, ndept, ncont, nbn, id);
    }

    private void deleteOfficers() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter the ID to delete: ");
        int id = 0;

        while (!sc.hasNextInt()) {
            System.out.print("Invalid ID. Enter a valid numeric ID: ");
            sc.next();
        }
        id = sc.nextInt();

        while (conf.getSingleValue("SELECT officer_id FROM tbl_officer WHERE officer_id = ?", id) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Select Officer ID Again: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid ID. Enter a valid numeric ID: ");
                sc.next();
            }
            id = sc.nextInt();
        }

        String qry = "DELETE FROM tbl_officer WHERE officer_id = ?";
        conf.deleteRecord(qry, id);
    }
}
