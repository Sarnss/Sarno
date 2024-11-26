package blottersystemsarno;

import java.util.Scanner;

import java.util.Scanner;

public class BLOTTERSYSTEMSARNO {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean exit = true;

        do {
            System.out.println("|----------------------------------------------------------|");
            System.out.println("|                 WELCOME TO BLOTTER SYSTEM                |");
            System.out.println("|----------------------------------------------------------|");
            System.out.println("");
            System.out.println("1. [COMPLAINANT]");
            System.out.println("2. [ENTRY]");
            System.out.println("3. [OFFICER]");
            System.out.println("4. [SUSPECT]");
            System.out.println("5. [REPORTS]");
            System.out.println("6. [EXIT]");

            System.out.print("Enter Action: ");
            int act = 0;

            if (sc.hasNextInt()) {
                act = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                sc.next();    
                continue;
            }

            switch (act) {
                case 1:
                    Complainant cn = new Complainant();
                    cn.cTransaction();
                    break;

                case 2:
                    Entry en = new Entry();
                    en.entryTransaction();
                    break;

                case 3:
                    Officer off = new Officer();
                    off.officerTransaction();
                    break;

                case 4:
                    Suspect st = new Suspect();
                    st.sTransaction();
                    break;

                case 5:
                    Reports it = new Reports();
                    it.incidentTransaction();
                    break;

                case 6:
                    System.out.println("ARE YOU SURE YOU WANT TO EXIT? (yes/no): ");
                    String resp = sc.next();
                    while (!resp.equalsIgnoreCase("yes") && !resp.equalsIgnoreCase("no")) {
                        System.out.print("Invalid response. Enter 'yes' to exit or 'no' to return: ");
                        resp = sc.next();
                    }
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = false;
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 6.");
            }
        } while (exit);

        System.out.println("Thank you, See you soon!");
    }
}
