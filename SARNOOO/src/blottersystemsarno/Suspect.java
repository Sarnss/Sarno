package blottersystemsarno;

import java.util.Scanner;

public class Suspect {

    public void sTransaction() {

        Scanner sc = new Scanner(System.in);
        String response = null;
        do {

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
            int action = 0;

            if (sc.hasNextInt()) {
                action = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                sc.next(); 
                continue;
            }

            Suspect st = new Suspect();

            switch (action) {
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

    public void addSuspects() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Suspect Name: ");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter Suspect Name: ");
            name = sc.nextLine();
        }

        System.out.print("Age: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid age. Enter a valid numeric Age: ");
            sc.next();
        }
        int age = sc.nextInt();
        while (age <= 0) {
            System.out.print("Age must be greater than 0. Enter Age: ");
            age = sc.nextInt();
        }

        System.out.print("Gender (M/F): ");
        String gender = sc.next();
        while (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
            System.out.print("Invalid gender. Enter 'M' for Male or 'F' for Female: ");
            gender = sc.next();
        }

        sc.nextLine(); 
        System.out.print("Address: ");
        String add = sc.nextLine();
        while (add.isEmpty()) {
            System.out.print("Address cannot be empty. Enter Address: ");
            add = sc.nextLine();
        }

        String sql = "INSERT INTO tbl_suspect (s_name, s_age, s_gender, s_address) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, name, age, gender, add);
    }

    public void viewSuspects() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_suspect";
        String[] Headers = {"SuspectID", "Suspect", "Age", "Gender", "Address"};
        String[] Columns = {"s_id", "s_name", "s_age", "s_gender", "s_address"};

        conf.viewRecords(Query, Headers, Columns);
    }

    private void updateSuspects() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter the ID to update: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid ID. Enter a valid numeric ID: ");
            sc.next();
        }
        int id = sc.nextInt();

        while (conf.getSingleValue("SELECT s_id FROM tbl_suspect WHERE s_id = ?", id) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Select Suspect ID Again: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid ID. Enter a valid numeric ID: ");
                sc.next();
            }
            id = sc.nextInt();
        }

        sc.nextLine(); 
        System.out.print("New Suspect Name: ");
        String nname = sc.nextLine();
        while (nname.isEmpty()) {
            System.out.print("Name cannot be empty. Enter New Suspect Name: ");
            nname = sc.nextLine();
        }

        System.out.print("New Age: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid age. Enter a valid numeric Age: ");
            sc.next();
        }
        int nage = sc.nextInt();
        while (nage <= 0) {
            System.out.print("Age must be greater than 0. Enter Age: ");
            nage = sc.nextInt();
        }

        System.out.print("New Gender (M/F): ");
        String ngender = sc.next();
        while (!ngender.equalsIgnoreCase("M") && !ngender.equalsIgnoreCase("F")) {
            System.out.print("Invalid gender. Enter 'M' for Male or 'F' for Female: ");
            ngender = sc.next();
        }

        sc.nextLine(); 
        System.out.print("New Address: ");
        String nadd = sc.nextLine();
        while (nadd.isEmpty()) {
            System.out.print("Address cannot be empty. Enter New Address: ");
            nadd = sc.nextLine();
        }

        String qry = "UPDATE tbl_suspect SET s_name = ?, s_age = ?, s_gender = ?, s_address = ? WHERE s_id = ?";
        conf.updateRecord(qry, nname, nage, ngender, nadd, id);
    }

    private void deleteSuspects() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter the ID to delete: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid ID. Enter a valid numeric ID: ");
            sc.next();
        }
        int id = sc.nextInt();

        while (conf.getSingleValue("SELECT s_id FROM tbl_suspect WHERE s_id = ?", id) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Select Suspect ID Again: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid ID. Enter a valid numeric ID: ");
                sc.next();
            }
            id = sc.nextInt();
        }

        String qry = "DELETE FROM tbl_suspect WHERE s_id = ?";
        conf.deleteRecord(qry, id);
    }
}
