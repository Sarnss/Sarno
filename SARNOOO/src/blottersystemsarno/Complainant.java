package blottersystemsarno;

import java.util.Scanner;

public class Complainant {

    public void cTransaction() {

        Scanner sc = new Scanner(System.in);
        String response;
        do {

            System.out.println("|-------------------|");
            System.out.println("|WELCOME COMPLAINANT|");
            System.out.println("|-------------------|");
            System.out.println("");
            System.out.println("1. [ADD COMPLAINANT]");
            System.out.println("2. [VIEW COMPLAINANT]");
            System.out.println("3. [UPDATE COMPLAINANT]");
            System.out.println("4. [DELETE COMPLAINANT]");
            System.out.println("5. [EXIT]");

            System.out.print("Enter Action: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                sc.next();
            }
            int action = sc.nextInt();

            Complainant cn = new Complainant();

            switch (action) {
                case 1:
                    cn.addComplainants();
                    break;
                case 2:
                    cn.viewComplainants();
                    break;
                case 3:
                    cn.viewComplainants();
                    cn.updateComplainants();
                    cn.viewComplainants();
                    break;
                case 4:
                    cn.viewComplainants();
                    cn.deleteComplainants();
                    cn.viewComplainants();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 5.");
            }
            System.out.println("Do you want to continue? (yes/no)");
            response = sc.next();
        } while (response.equalsIgnoreCase("yes"));
        System.out.println("Thank You, See you soonest!");
    }

    public void addComplainants() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Complainant Name: ");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter Complainant Name: ");
            name = sc.nextLine();
        }

        System.out.print("Age: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid age. Enter a valid number: ");
            sc.next();
        }
        int age = sc.nextInt();

        System.out.print("Gender (Male/Female): ");
        String gender = sc.next();
        while (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
            System.out.print("Invalid gender. Enter Male or Female: ");
            gender = sc.next();
        }

        System.out.print("Address: ");
        sc.nextLine(); 
        String add = sc.nextLine();
        while (add.isEmpty()) {
            System.out.print("Address cannot be empty. Enter Address: ");
            add = sc.nextLine();
        }

        String sql = "INSERT INTO tbl_complainant (c_name, c_age, c_gender, c_address) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, name, age, gender, add);
    }

    public void viewComplainants() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_complainant";
        String[] Headers = {"ComplainantID", "Complainant", "Age", "Gender", "Address"};
        String[] Columns = {"c_id", "c_name", "c_age", "c_gender", "c_address"};

        conf.viewRecords(Query, Headers, Columns);
    }

    private void updateComplainants() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter the ID to update: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid ID. Enter a valid number: ");
            sc.next();
        }
        int id = sc.nextInt();

        while (conf.getSingleValue("SELECT c_id FROM tbl_complainant WHERE c_id = ?", id) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Select Complainant ID Again: ");
            id = sc.nextInt();
        }

        System.out.print("New Complainant Name: ");
        sc.nextLine(); 
        String nname = sc.nextLine();
        while (nname.isEmpty()) {
            System.out.print("Name cannot be empty. Enter New Complainant Name: ");
            nname = sc.nextLine();
        }

        System.out.print("New Age: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid age. Enter a valid number: ");
            sc.next();
        }
        int nage = sc.nextInt();

        System.out.print("New Gender (Male/Female): ");
        String ngender = sc.next();
        while (!ngender.equalsIgnoreCase("Male") && !ngender.equalsIgnoreCase("Female")) {
            System.out.print("Invalid gender. Enter Male or Female: ");
            ngender = sc.next();
        }

        System.out.print("New Address: ");
        sc.nextLine(); 
        String nadd = sc.nextLine();
        while (nadd.isEmpty()) {
            System.out.print("Address cannot be empty. Enter New Address: ");
            nadd = sc.nextLine();
        }

        String qry = "UPDATE tbl_complainant SET c_name = ?, c_age = ?, c_gender = ?, c_address = ? WHERE c_id = ?";
        conf.updateRecord(qry, nname, nage, ngender, nadd, id);
    }

    private void deleteComplainants() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter the ID to delete: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid ID. Enter a valid number: ");
            sc.next();
        }
        int id = sc.nextInt();

        while (conf.getSingleValue("SELECT c_id FROM tbl_complainant WHERE c_id = ?", id) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Select Complainant ID Again: ");
            id = sc.nextInt();
        }

        String qry = "DELETE FROM tbl_complainant WHERE c_id = ?";
        conf.deleteRecord(qry, id);
    }
}

