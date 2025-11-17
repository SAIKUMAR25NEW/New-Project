// File: BankTransferDemo.java
package Week3Day2;

public class BankTransferDemo {

    public static void main(String[] args) {

        // Create two bank accounts (objects)
        BankAccount acc1 = new BankAccount("Alice", 1000);
        BankAccount acc2 = new BankAccount("Bob", 700);

        // Display initial balances
        System.out.println("--- Initial Balances ---");
        acc1.displayBalance();
        acc2.displayBalance();

        // Perform transfer
        System.out.println("\n--- Transferring $300 from Alice to Bob ---");
        acc1.transfer(acc2, 300); // Transfer 300 from Alice → Bob

        // Display updated balances
        System.out.println("\n--- Updated Balances ---");
        acc1.displayBalance();
        acc2.displayBalance();
    }
}

// BankAccount class (supports deposit, withdraw, transfer)
class BankAccount {
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(accountHolder + " deposited $" + amount);
        } else {
            System.out.println("❌ Invalid deposit amount for " + accountHolder);
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(accountHolder + " withdrew $" + amount);
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance for " + accountHolder);
        } else {
            System.out.println("❌ Invalid withdrawal amount.");
        }
    }

    // Transfer method — core concept: object interaction
    public void transfer(BankAccount receiver, double amount) {
        if (amount > 0 && amount <= balance) {
            // Deduct from sender
            this.balance -= amount;

            // Add to receiver’s balance
            receiver.balance += amount;

            System.out.println("✅ $" + amount + " transferred from " + this.accountHolder + " to " + receiver.accountHolder);
        } else if (amount > balance) {
            System.out.println("❌ Transfer failed. " + accountHolder + " has insufficient funds.");
        } else {
            System.out.println("❌ Invalid transfer amount.");
        }
    }

    // Display current balance
    public void displayBalance() {
        System.out.println("💰 " + accountHolder + "'s Balance: $" + balance);
    }
}

