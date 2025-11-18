package Week4Day2;

// Base class
class BankAccount {
    protected String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Method to calculate yearly interest (default version)
    public double calculateInterest() {
        return balance * 0.03; // Default 3% interest
    }

    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
    }
}

// Derived class 1 - Savings Account
class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    // Overriding calculateInterest() method
    @Override
    public double calculateInterest() {
        return balance * 0.05; // Savings gets 5% interest
    }
}

// Derived class 2 - Checking Account
class CheckingAccount extends BankAccount {

    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    // Overriding calculateInterest() method
    @Override
    public double calculateInterest() {
        return balance * 0.02; // Checking gets 2% interest
    }
}

// Main class to demonstrate polymorphism
public class PolymorphismDemo {
    public static void main(String[] args) {

        // Create parent class reference variables
        BankAccount acc1 = new SavingsAccount("SAV1001", 5000);
        BankAccount acc2 = new CheckingAccount("CHK2001", 8000);

        // Polymorphic behavior — same method call, different outputs
        System.out.println("--- Savings Account ---");
        acc1.displayDetails();
        System.out.println("Yearly Interest: $" + acc1.calculateInterest());

        System.out.println("\n--- Checking Account ---");
        acc2.displayDetails();
        System.out.println("Yearly Interest: $" + acc2.calculateInterest());
    }
}

