// File: BankAccountDemo.java
package Week3Day1;

import java.util.Scanner;

// Define the BankAccount class
class BankAccount {
    private String accountHolder;
    private double balance;

    // Constructor (runs automatically when an object is created)
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited: $" + amount);
        } else {
            System.out.println("❌ Deposit amount must be positive.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawn: $" + amount);
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance.");
        } else {
            System.out.println("❌ Invalid withdrawal amount.");
        }
    }

    // Show current balance
    public void displayBalance() {
        System.out.println("💰 Current balance: $" + balance);
    }
}

// Main class to test the BankAccount class
public class BankAccountDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create account
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit: ");
        double initial = sc.nextDouble();

        // Create BankAccount object
        BankAccount account = new BankAccount(name, initial);

        // Perform some transactions
        account.displayBalance();
        account.deposit(500);
        account.withdraw(200);
        account.displayBalance();

        sc.close();
    }
}

