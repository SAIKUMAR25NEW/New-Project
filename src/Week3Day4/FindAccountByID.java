package Week3Day4;

import java.util.Scanner;

public class FindAccountByID {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create an array of BankProfile objects
        BankProfile[] profiles = new BankProfile[3];
        profiles[0] = new BankProfile("ACC1001", "Alice", 1500.0);
        profiles[1] = new BankProfile("ACC1002", "Bob", 2200.5);
        profiles[2] = new BankProfile("ACC1003", "Charlie", 1800.75);

        // Ask user for the Account ID to search
        System.out.print("Enter Account ID to search: ");
        String searchID = sc.nextLine();

        // Search for profile using a loop
        boolean found = false;
        for (BankProfile profile : profiles) {
            if (profile.getAccountID().equalsIgnoreCase(searchID)) {
                System.out.println("\nAccount Found:");
                profile.displayProfile();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nAccount with ID " + searchID + " not found.");
        }

        sc.close();
    }
}

class BankProfile {
    private String accountID;
    private String accountHolder;
    private double balance;

    // Constructor
    public BankProfile(String accountID, String accountHolder, double balance) {
        this.accountID = accountID;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Getter for accountID
    public String getAccountID() {
        return accountID;
    }

    // Display account details
    public void displayProfile() {
        System.out.println("Account ID: " + accountID);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
    }
}
