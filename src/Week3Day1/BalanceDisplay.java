package Week3Day1;

import java.util.Scanner;

public class BalanceDisplay {

    // Class variable to store account balance
    private double balance;

    // Constructor to initialize the balance
    public BalanceDisplay(double initialBalance) {
        balance = initialBalance;
    }

    // Method to display the balance
    public void displayBalance() {
        System.out.println("💰 Current Account Balance: $" + balance);
    }

    // Main method (program starts here)
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Create Scanner object for input

        // Ask user for initial balance
        System.out.print("Enter your initial account balance: ");
        double userBalance = sc.nextDouble(); // Read user input

        // Create an object of BalanceDisplay with user's input
        BalanceDisplay account = new BalanceDisplay(userBalance);

        // Display balance
        account.displayBalance();

        sc.close(); // Close Scanner (good practice)
    }
}

